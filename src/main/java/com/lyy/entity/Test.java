package com.lyy.entity;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.*;

/**
 * Created by liYueYang on 2021/12/23.
 */
public class Test {

    public static void main(String[] args) {
//        List<Person> list = Lists.newArrayList();
//        //产生10以内的随机数
//        int num = (int) (Math.random() * 1000 + 1);
//        for (int i = num; i > 0; i--) {
//            list.add(new Person(i, "张三", "河南"));
//        }
//        for (Object o : list) {
//            System.out.println(o);
//        }
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        Collections.sort(list, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                if (o1.getId() > o2.getId()) {
//                    return 1;
//                } else if (o1.getId() < o2.getId()) {
//                    return -1;
//                }
//                return 0;
//            }
//
//        });
//        for (Object o : list) {
//            System.out.println(o);
//        }

//        Date date1 = DateUtil.parse("2021-12-21 09:00:00");
//        Date date2 = DateUtil.parse("2021-12-22 09:00:00");
//        if (date1.getTime() > date2.getTime())//比较时间大小,如果dt1大于dt2
//        {
//            System.out.println("yes");
//
//        } else {
//            System.out.println("no");
//        }

        System.out.println(getFiles("F:\\sl_photo\\20220104", "30100005"));
    }


    public static String getFiles(String path, String guid) {
        List<String> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                if (tempList[i].getName().contains(guid)) {
                    files.add(tempList[i].getName());
                }
            }
        }
        Collections.sort(files);
        return files.get(files.size() -1);
    }

}
