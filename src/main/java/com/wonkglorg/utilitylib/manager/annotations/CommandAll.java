package com.wonkglorg.utilitylib.manager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandAll {
    String permission() default "";

    int cooldown() default 0;

}
