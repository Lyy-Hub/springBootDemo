package com.lyy.service.impl;

import com.lyy.entity.UserEntity;
import com.lyy.others.utils.ConvertDateTime;
import com.lyy.repo.UserEntityRepo;
import com.lyy.service.api.ExcelOperationService;
import com.lyy.others.utils.Utils;
import com.lyy.others.utils.excelUtil.ExcelData;
import com.lyy.others.utils.excelUtil.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.lyy.others.utils.excelUtil.ExcelUtil.importExcel;

/**
 * Created by liyueyang on 2019/6/4.
 */
@Service
public class ExcelOperationServiceImpl implements ExcelOperationService {
    @Autowired
    private UserEntityRepo userEntityRepo;

    @Override
    public Boolean exportE(HttpServletResponse response, String fileName) {
        //查询数据并赋值给ExcelData
        List<UserEntity> userEntityList = userEntityRepo.findAll();
        List<String[]> list = new ArrayList<String[]>();
        for (UserEntity userEntity : userEntityList) {
            String[] arrs = new String[userEntityList.size()];
            arrs[0] = String.valueOf(userEntity.getUserName());
            arrs[1] = String.valueOf(userEntity.getAddress());
            arrs[2] = String.valueOf(userEntity.getStatus());
            arrs[3] = String.valueOf(ConvertDateTime.calender2Str(userEntity.getCreateTime()));
            list.add(arrs);
        }
        //表头赋值
        String[] head = {"姓名", "地址", "状态", "时间"};
        ExcelData data = new ExcelData();
        data.setHead(head);
        data.setData(list);
        data.setFileName(fileName);
        //实现导出
        try {
            ExcelUtil.exportExcel(response, data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean importE(String fileName) {
        try {
            List<Object[]> list = importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(UUID.randomUUID().toString());
                userEntity.setUserName((String) list.get(i)[0]);
                userEntity.setAddress((String) list.get(i)[1]);
                userEntity.setStatus((String) list.get(i)[2]);
                userEntity.setPassword(Utils.md5((String) list.get(i)[3]));
                userEntity.setSex((String) list.get(i)[4]);
                userEntity.setCreateTime(Calendar.getInstance());
                System.out.println(userEntity.toString());
                userEntityRepo.save(userEntity);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
