package com.lyy.service;

import com.lyy.entity.UserEntity;
import com.lyy.pojo.*;
import com.lyy.redis.JedisClient;
import com.lyy.repo.UserEntityRepo;
import com.lyy.service.copier.UserInfoCopier;
import com.lyy.utils.JwtUtil;
import com.lyy.utils.Utils;
import org.coodex.util.Common;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by liyueyang on 2019/5/24.
 */
@Service
public class UserManagementService {

    @Autowired
    private UserEntityRepo userEntityRepo;
    @Autowired
    private UserInfoCopier userInfoCopier;
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);

    /**
     * @param loginInfo
     * @return
     */
    public ResponseInfo login(LoginInfo loginInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        String password = Utils.md5(loginInfo.getPassword());
        String name = loginInfo.getUserName();
        UserEntity userEntity = userEntityRepo.findByUserNameAndPasswordAndStatus(name, password, "1");
        if (null == userEntity) {
            responseInfo.setCode(0);
            responseInfo.setInfo("用户名或密码错误");
            return responseInfo;
        } else {
            String jwt = JwtUtil.createJWT(Common.getUUIDStr(), userEntity.getUserName(), SystemConstant.JWT_TTLMILIS);
            responseInfo.setCode(SystemConstant.SUCCESS);
            responseInfo.setInfo(jwt);
            return responseInfo;
        }
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    public ResponseInfo addUser(UserInfo userInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (null != userInfo) {
            final String key = userInfo.getUserName();
            UserEntity userEntity = userEntityRepo.findByUserNameAndStatus(key, "1");
            if (null == userEntity) {
                UserEntity userEntity1 = new UserEntity();
                BeanUtils.copyProperties(userInfo, userEntity1);
                userEntity1.setId(Common.getUUIDStr());
                userEntity1.setStatus("1");
                userEntity1.setPassword(Utils.md5(userInfo.getPassword()));
                userEntity1.setCreateTime(Calendar.getInstance());
                userEntityRepo.save(userEntity1);
                responseInfo.setInfo("添加成功");
                responseInfo.setCode(SystemConstant.SUCCESS);
                return responseInfo;
            } else {
                responseInfo.setInfo("用户名已存在");
                responseInfo.setCode(SystemConstant.USERNAME_HAS_EXIST);
                return responseInfo;
            }
        }
        responseInfo.setInfo("失败");
        responseInfo.setCode(SystemConstant.FAIL);
        return responseInfo;
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    public ResponseInfo deleteUser(String[] ids) {
        ResponseInfo responseInfo = new ResponseInfo();
        for (String id : ids){
            userEntityRepo.delete(id);
        }
        responseInfo.setCode(SystemConstant.SUCCESS);
        responseInfo.setInfo("删除成功");
        return responseInfo;
    }

    /**
     * @param userInfo
     * @return ResponseInfo
     */
    public ResponseInfo updateUser(final UserInfo userInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (null != userInfo) {
            UserEntity userEntity = userEntityRepo.findOne(userInfo.getId());
            BeanUtils.copyProperties(userInfo, userEntity);
            userEntityRepo.save(userEntity);

            /*//延时100ms再次删除缓存中用户信息，避免其他线程读取到脏数据
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    JedisClient.delete(key);
                    System.out.println("delete ok");
                }
            }, 100, TimeUnit.MILLISECONDS);*/
            responseInfo.setCode(SystemConstant.SUCCESS);
            responseInfo.setInfo("成功");
            return responseInfo;
        } else {
            responseInfo.setCode(SystemConstant.DATA_IS_NULL);
            responseInfo.setInfo("数据为空");
            return responseInfo;
        }
    }

    /**
     * 分页查询
     * @param param
     * @return
     */
    public PageResult<UserInfo> findUser(com.lyy.pojo.PageRequest<UserParam> param){
        final UserParam userParam = param.getParamContent();
        Specification spec = new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(userParam.getUserName())){
                    predicateList.add(criteriaBuilder.equal((root.<String>get("userName")),userParam.getUserName()));
                }
                if (!StringUtils.isEmpty(userParam.getStatus())){
                    predicateList.add(criteriaBuilder.equal((root.<String>get("status")),userParam.getStatus()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"userName");
        Pageable pageable = new PageRequest(param.getNum(),param.getSize(),sort);
        Page<UserEntity> page = userEntityRepo.findAll(spec,pageable);
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        List<UserEntity> userEntities = page.getContent();

        for (UserEntity u : userEntities) {
            UserInfo userInfo = userInfoCopier.copy(u,new UserInfo());
            userInfos.add(userInfo);
        }
        PageResult<UserInfo> pageResult = new PageResult<UserInfo>();
        pageResult.setContent(userInfos);
        pageResult.setNum(page.getNumber());
        pageResult.setSize(page.getSize());
        pageResult.setTotal(page.getTotalElements());
        pageResult.setTotalPages(page.getTotalPages());
        return pageResult;
    }
}
