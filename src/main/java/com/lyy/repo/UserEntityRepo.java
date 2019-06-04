package com.lyy.repo;

import com.lyy.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liyueyang on 2019/5/24.
 */
public interface UserEntityRepo extends CrudRepository<UserEntity,String>,JpaSpecificationExecutor<UserEntity>,
        PagingAndSortingRepository<UserEntity,String> {

    UserEntity findByUserNameAndPasswordAndStatus(String name,String password,String status);

    UserEntity findByUserNameAndStatus(String name,String status);

    List<UserEntity> findAll();
}
