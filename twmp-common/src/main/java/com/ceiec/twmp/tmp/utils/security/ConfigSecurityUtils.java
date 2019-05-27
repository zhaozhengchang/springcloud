package com.ceiec.twmp.tmp.utils.security;

import org.springframework.cloud.context.encrypt.EncryptorFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * CreateDate：2018/4/20 <br/>
 * Author：wenliang <br/>
 * Description: security utils for config-server connection
 **/
public class ConfigSecurityUtils {

    /** encrypt key for config-server connection */
    private static final String ENCRYPT_KEY = "0e010e17-2529-4581-b907-c8edcfd6be09";

    /** encrypt mark label */
    private static String ENCRYPT_LABEL = "{cipher}";

    /**
     * encrypt by spring-boot-security
     *
     * @param text the text to be encrypt
     * @return String the text after encrypt
     */
    public static String encrypt(String text) {
        TextEncryptor encryptor = new EncryptorFactory().create(ENCRYPT_KEY);
        return ENCRYPT_LABEL + encryptor.encrypt(text);
    }

    /**
     * decrypt by spring-boot-security
     *
     * @param text the text to be decrypt
     * @return String the text after decrypt
     */
    public static String decrypt(String text) {
        TextEncryptor encryptor = new EncryptorFactory().create(ENCRYPT_KEY);
        if (text.startsWith(ENCRYPT_LABEL)) {
            text = text.substring(ENCRYPT_LABEL.length());
            return encryptor.decrypt(text);
        }
        return text; //origin text is plain, do not need to decrypt
    }

    /**
     * get encrypt key for spring-boot-security
     *
     * @return String encrypt key
     */
    public static String getEncryptKey() {
        return ENCRYPT_KEY;
    }
}
