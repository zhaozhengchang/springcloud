package com.ceiec.twmp.tmp.utils.annotations;

import java.lang.annotation.*;

/**
 * CreateDate：2018/5/14 <br/>
 * Author：wenliang <br/>
 * Description: annotation to label don't need login token interfaces
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreToken {
}
