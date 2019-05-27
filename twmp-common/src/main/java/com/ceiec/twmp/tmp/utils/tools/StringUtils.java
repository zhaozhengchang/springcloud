package com.ceiec.twmp.tmp.utils.tools;

/**
 * @packge com.ceiec.twmp.utils.tools
 * @date 2019年1月3日 下午1:24:25
 * @author wenliang
 * @comment String Utils
 * @update
 */

public class StringUtils {

	/**
	 * 判断字符串为空
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string){
		if(string == null || "".equals(string) || "null".equals(string)){
			return true;
		}
		return false;
	}



	/**
	 * 简单数组用分隔符拼接成字符串(仅支持基本数据类型包装类)
	 * @param simpleArray
	 * @param separator
	 * @return
	 */
	public static String simpleArrayToString(Object[] simpleArray, String separator) {
		StringBuffer resultStr = new StringBuffer("");
		if (simpleArray == null || simpleArray.length == 0) {
			return resultStr.toString();
		}
		for (Object obj : simpleArray) {
			resultStr.append(String.valueOf(obj));
			resultStr.append(separator);
		}
		return resultStr.substring(0, resultStr.toString().length() - 1);
	}

	/**
	 * String数组用分隔符拼接成字符串
	 * @param simpleArray
	 * @param separator
	 * @return
	 */
	public static String stringArrayToString(String[] simpleArray, String separator) {
		StringBuffer resultStr = new StringBuffer("");
		if (simpleArray == null || simpleArray.length == 0) {
			return resultStr.toString();
		}
		for (Object obj : simpleArray) {
			resultStr.append("'");
			resultStr.append(String.valueOf(obj));
			resultStr.append("'");
			resultStr.append(separator);
		}
		return resultStr.substring(0, resultStr.toString().length() - 1);
	}
	
	/**
	 * 替换sql参数中的%和_,避免sql注入
	 * @param sqlparameters
	 * @return
	 */
	public static String replaceSqlparameters(String sqlparameters){
		String sqlParamter = sqlparameters;
		if(null != sqlParamter){
			sqlParamter = sqlParamter.replaceAll("_", "\\\\_").replaceAll("%", "\\\\%");
		}
		return sqlParamter;
	}
	/**
	 * String字符串转化为ASCII码的字符串
	 * @param valueString
	 * @return valueASCII
	 */
	 public static String stringToAscii(String valueString)
	 {
	  StringBuffer sbu = new StringBuffer();
	  char[] chars = valueString.toCharArray();
	  for (int i = 0; i < chars.length; i++) {
	   if(i != chars.length - 1)
	   {
	    sbu.append((int)chars[i]).append(",");
	   }
	   else {
	    sbu.append((int)chars[i]);
	   }
	  }
	  return sbu.toString();
	 } 

     /**
	  * ASCII码的字符串转化为String字符串
	  * @param  valueASCII
	  * @return stringValue
	 */
	 public static String asciiToString(String valueASCII)
	 {
	  StringBuffer sbu = new StringBuffer();
	  String[] chars = valueASCII.split(",");
	  for (int i = 0; i < chars.length; i++) {
	   sbu.append((char) Integer.parseInt(chars[i]));
	  }
	  return sbu.toString();
	 }
	
}
