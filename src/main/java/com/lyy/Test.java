package com.lyy;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liYueYang on 2019/9/4.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
//        String strPassword = "Unknown";
//        char[] charPassword = new char[]{'U', 'n', 'k', 'w', 'o', 'n'};
//        System.out.println("字符密码：" + strPassword);
//        System.out.println("字符密码：" + Arrays.toString(charPassword));
//        logger.error("字符密码：" + strPassword);
//        logger.error("字符密码：" + Arrays.toString(charPassword));

        int aa = DateUtil.getBeginValue(Calendar.getInstance(),1);
        System.out.println(aa);
    }
}
