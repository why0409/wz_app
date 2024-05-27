package com.ruoyi.web.annotation;

import java.lang.annotation.*;

/**
 * @Author: LJW
 * @Date: 2024/4/28 0028 14:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClickLog {
    String description() default "";
}
