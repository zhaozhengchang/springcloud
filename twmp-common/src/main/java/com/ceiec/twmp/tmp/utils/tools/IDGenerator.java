package com.ceiec.twmp.tmp.utils.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * CreateDate：2017/11/1
 * Author：wenliang
 * Description: generate primary key id for database table
 **/
public class IDGenerator {

    /**
     * generate 32bit UUID
     *
     * @return 32bit UUID
     */
    public static String generate32UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * encode input string by MD5 and generate 32bit ID
     *
     * @param input input stream
     * @return generated id
     */
    public static String generate32MD5ID(String input) {
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest(input.getBytes("utf-8"));
            //change 128bits binary array to 16bits hexadecimal String
            StringBuilder md5Str = new StringBuilder();
            int temp;
            for (byte b : bytes) {
                temp = b;
                if (temp < 0) {
                    temp += 256;
                }
                if (temp < 16) {
                    md5Str.append("0");
                }
                md5Str.append(Integer.toHexString(temp));
            }
            return md5Str.toString().toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get random code including upper case letter, lower case letter and number
     *
     * @return random code
     */
    public static String generateRandomCode() {
        StringBuilder randomCode = new StringBuilder("");
        char[] arr = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57,   //number form 0 to 9
                //letter from A to Z
                65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                //letter from a to z
                97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
        int i = 1;
        while (i++ <= 3) { //get three times from array
            char c = arr[new Random().nextInt(arr.length)];
            randomCode.append(c);
        }
        return randomCode.toString();
    }
}
