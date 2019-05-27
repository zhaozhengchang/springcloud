package com.ceiec.twmp.tmp.utils.tools;

import java.util.Date;

/**
 * CreateDate：2018/5/17 <br/>
 * Author：wenliang <br/>
 * Description: token related utils
 **/
public class TokenUtils {

    private static final String LINK = "-";

    /**
     * generate a login token after login success
     *
     * @param systemCode request system code
     * @param userID logged in user ID
     * @param userName logged in user name
     * @param passWord logged in passWord
//     * @param loginIP logged IP
     * @return a new login token
     */
    public static String generateToken(int systemCode, String userID, String userName, String passWord) {
        String encryptText = DESUtils.encrypt(systemCode + LINK + userID + LINK + userName + LINK + passWord+ LINK  + System.currentTimeMillis());
        return encryptText.replaceAll("\\r", "").replaceAll("\\n", "");
    }

    /**
     * get system code that token belongs
     *
     * @param token login token
     * @return system code
     */
    public static int getSystemCode(String token) {
        return Integer.parseInt(DESUtils.decrypt(token).split(LINK)[0]);
    }

    /**
     * get login user ID from login token
     *
     * @param token login token
     * @return login user ID
     */
    public static String getUserID(String token) {
        return DESUtils.decrypt(token).split(LINK)[1];
    }

    /**
     * get login user name from login token
     *
     * @param token login token
     * @return login user name
     */
    public static String getUserName(String token) {
        return DESUtils.decrypt(token).split(LINK)[2];
    }

    /**
     * get getPassWord from login token
     *
     * @param token login token
     * @return getPassWord
     */
    public static String getPassWord(String token) {
        return DESUtils.decrypt(token).split(LINK)[3];
    }

    /**
     * get login IP from login token
     *
     * @param token login token
     * @return login IP
     */
    public static String getLoginIP(String token) {
        return DESUtils.decrypt(token).split(LINK)[4];
    }

    /**
     * get login time from login token
     *
     * @param token login token
     * @return login time
     */
    public static Date getLoginTime(String token) {
        return new Date(Long.parseLong(DESUtils.decrypt(token).split(LINK)[5]));
    }

    /**
     * charge if given token is a legal token
     *
     * @param token given token
     * @return legal verify result
     */
    public static boolean isLegalToken(String token) {
        try {
            String text = DESUtils.decrypt(token);
            return text.split(LINK).length - 1 == 4;
        } catch (Exception e) {
            return false;
        }
    }
}
