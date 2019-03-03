package com.xadevpos.demo.Util;

import java.util.UUID;

public class DateUtil {

    public static String getDateStr(){
        Long time = System.currentTimeMillis();
        return time.toString();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
