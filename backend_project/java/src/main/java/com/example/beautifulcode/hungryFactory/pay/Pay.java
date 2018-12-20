package com.example.beautifulcode.hungryFactory.pay;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//  ElementType.TYPE 作用于 class ElementType
@Target(ElementType.TYPE)

//作用于classes 文件
@Retention(RetentionPolicy.RUNTIME)
public @interface Pay {
    int channelId();
}
