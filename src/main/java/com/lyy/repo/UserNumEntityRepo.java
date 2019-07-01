package com.lyy.repo;

import com.lyy.entity.UserNumEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyueyang on 2019/7/1.
 */
public interface UserNumEntityRepo extends CrudRepository<UserNumEntity,String>,JpaSpecificationExecutor<UserNumEntity>,
        PagingAndSortingRepository<UserNumEntity,String> {
}
