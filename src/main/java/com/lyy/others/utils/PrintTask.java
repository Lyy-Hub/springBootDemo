package com.lyy.others.utils;

import com.lyy.entity.UserNumEntity;
import com.lyy.repo.UserEntityRepo;
import com.lyy.repo.UserNumEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * 定时器测试
 * Created by liyueyang on 2019/7/1.
 */
@Component
public class PrintTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private UserEntityRepo userEntityRepo;
    @Autowired
    private UserNumEntityRepo userNumEntityRepo;

    /**
     * 1、@Scheduled(fixedRate = 5000) ：上一次执行开始时间点之后5秒再执行
     * 2、@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     * 3、@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，
     *  之后按fixedRate的规则每5秒执行一次。initialDelay表示第一次调用前的延时，单位毫秒，必需配合cron、fixedDelay或者fixedRate来使用。
     */
    // 4、@Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则，与fixedDelay类似，
    //  上次执行完毕后才进行下次调度。
    // 3 秒
    //@Scheduled(fixedRate = 3000)
    // 每天
    @Scheduled(cron = "0 0 * * * ?")
    public void reportCurrentTime() {
        // 执行任务 - 统计一次用户表中的人数，并存入用户数量表（仅测试，无实际业务意义）
        long num = userEntityRepo.count();
        UserNumEntity userNumEntity = new UserNumEntity();
        userNumEntity.setId(UUID.randomUUID().toString());
        userNumEntity.setNum(String.valueOf(num));
        userNumEntity.setCreateTime(Calendar.getInstance());
        System.out.println(ConvertDateTime.calender2Str(userNumEntity.getCreateTime()) + " 用户表总数为：" + num);
        userNumEntityRepo.save(userNumEntity);
    }

   /*@Scheduled(cron = "0/1 * * * * ?")
    public void task01() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " | task01 " + new Date().toLocaleString());
        Thread.sleep(2000);
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void task02() {
        System.out.println(Thread.currentThread().getName() + " | task02 " + new Date().toLocaleString());
    }*/
}
