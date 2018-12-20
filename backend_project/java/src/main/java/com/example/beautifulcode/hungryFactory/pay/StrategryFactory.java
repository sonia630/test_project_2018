package com.example.beautifulcode.hungryFactory.pay;


import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategryFactory {

    //1.生成 strateFactory 生成
    private static StrategryFactory factory = new StrategryFactory();

    public static StrategryFactory getInstance() {
        return factory;
    }

    public static HashMap<Integer, String> source_map = new HashMap<>();

    //根据channelId 创建 strategry  可以利用map, map的值,可以通过1.直接put  2.从文件读取 3.注解来
    static {
        //根据注解解析出来,放在map去, key 就是注解的值, value 就是类的路径 com.example.beautifulcode.hungryFactory.pay.imp.ICBCPay
        //通过反射封闭map 扫描包  pom加入包 org.reflections
        Reflections reflections = new Reflections("com.example.beautifulcode.hungryFactory.pay.impl");
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(Pay.class);
        //类上面有pay注解的拿回来,然后放在map中去
        for (Class clazz : classList) {
            Pay pay = (Pay) clazz.getAnnotation(Pay.class);
            source_map.put(pay.channelId(), clazz.getCanonicalName());
        }
        System.out.println("source_map:" + source_map.keySet().toString());

    }

    public Strategry create(int channelId) throws Exception {
        //根据 clannelid 来取class的路径  com.example.beautifulcode.hungryFactory.pay.imp.ICBCPay
        String clazz = source_map.get(channelId);
        Class clazz_ = Class.forName(clazz);
        return (Strategry) clazz_.newInstance();
    }
}
