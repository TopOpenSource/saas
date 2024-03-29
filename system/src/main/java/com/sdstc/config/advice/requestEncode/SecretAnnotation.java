package com.sdstc.config.advice.requestEncode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cheng
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecretAnnotation {
    boolean encode() default false;
    boolean decode() default false;
}
