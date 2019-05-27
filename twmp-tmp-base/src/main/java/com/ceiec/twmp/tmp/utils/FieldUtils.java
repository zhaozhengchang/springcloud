package com.ceiec.twmp.tmp.utils;

import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CreateDate：2018/5/4 <br/>
 * Author：wenliang <br/>
 * Description: transfer table field values to proper format for SQL provider
 **/
public class FieldUtils {

    /**
     * format table field value
     *
     * @param value table field value(must be in same type with database)
     * @return table field value in proper format
     */
    public static String format(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            String valueStr = (String) value;
            if (valueStr.contains("'")) {
                valueStr = valueStr.replaceAll("'", "\\\\'");
            }
            return "'" + valueStr + "'";
        }
        if (value instanceof Date) {
            Date date = (Date) value;
            String dateStr = DateUtils.format(date);
            return "str_to_date('" + dateStr + "','%Y-%m-%d %H:%i:%s')";
        }
        return "" + value + "";
    }

    /**
     * change list field to string field, divide with ","
     *
     * @param objectList object list
     * @return string field
     */
    public static <T> String listToStrField(List<T> objectList) {
        StringBuilder builder = new StringBuilder();
        for (T object : objectList) {
            builder.append(format(object)).append(",");
        }
        return builder.toString().replaceAll(",$", "");
    }

    /**
     * field string to field array
     *
     * @param fieldStr field string
     * @param divideConst what divided with
     * @return field array
     */
    public static List<String> strToListStrField(String fieldStr, String divideConst) {
        String[] fieldArray = fieldStr.split(divideConst);
        List<String> fieldList = new ArrayList<>();
        for (String field : fieldArray) {
            fieldList.add(field);
        }
        return fieldList;
    }

    /**
     * field string to field array
     *
     * @param fieldStr field string
     * @return field array
     */
    public static List<Integer> strToListIntField(String fieldStr) {
        String[] fieldArray = fieldStr.split(",");
        List<Integer> fieldList = new ArrayList<>();
        for (String field : fieldArray) {
            fieldList.add(Integer.parseInt(field));
        }
        return fieldList;
    }
}
