package com.ceiec.twmp.tmp.utils.security;

import com.ceiec.twmp.tmp.utils.tools.RandomStrGenerator;

/**
 * CreateDate：2018/4/18 <br/>
 * Author：wenliang <br/>
 * Description: config general authorize information
 **/
public class GeneralAuInfo {

    /** common properties files path */
    //public static final String COMMON_PROPERTIES_PATH = "https://raw.githubusercontent.com/joeneenie/sentiment2.0/master/";
    public static final String COMMON_PROPERTIES_PATH = "D://git_mine/twmp/twmp/";

    /** after zuul processed, the label key will be added */
    public static final String ZUUL_PROCESS_LABEL_KEY = "from_zuul_label";

    /** after zuul processed, the label value will be added */
    public static final String ZUUL_PROCESS_LABEL_VALUE = RandomStrGenerator.generateRandomStr(16);
}
