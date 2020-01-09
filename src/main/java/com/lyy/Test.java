package com.lyy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by liYueYang on 2019/9/4.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        String strPassword = "Unknown";
        char[] charPassword = new char[]{'U', 'n', 'k', 'w', 'o', 'n'};
        System.out.println("字符密码：" + strPassword);
        System.out.println("字符密码：" + Arrays.toString(charPassword));
        logger.error("字符密码：" + strPassword);
        logger.error("字符密码：" + Arrays.toString(charPassword));
    }
}
