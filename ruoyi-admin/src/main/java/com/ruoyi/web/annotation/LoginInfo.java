package com.ruoyi.web.annotation;

import java.lang.annotation.*;

/**
 * @author Adminstrators
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginInfo {
    String description() default "";
}
