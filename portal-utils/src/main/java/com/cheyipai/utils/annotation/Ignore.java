package com.cheyipai.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by andy on 16/4/26.
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ignore {
}