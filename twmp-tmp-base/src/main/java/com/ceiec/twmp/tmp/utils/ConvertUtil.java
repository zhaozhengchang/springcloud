package com.ceiec.twmp.tmp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @title: ConvertUtil </br>
 * @createDate：2019/3/14 14:49 </br>
 * @author：shihsh </br>
 * @description: 类型转换工具类 </br>
 **/


public class ConvertUtil {

    public static List<Long> stringToLongList(String strings, String separator) {
        if (strings == null || "".equals(strings)) {
            return null;
        }
        String[] strArr = strings.split(separator);
        List<Long> list = new ArrayList<>();
        for (String str : strArr) {
           list.add(Long.valueOf(str));
        }
        return list;
    }
}
