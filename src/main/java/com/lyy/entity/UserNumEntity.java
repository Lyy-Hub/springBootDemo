package com.lyy.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by liyueyang on 2019/7/1.
 */
@Entity
@Table(name = "t_user_num")
@Data
public class UserNumEntity {
    @Id
    private String id;
    private String num;
    private Calendar createTime;
}
