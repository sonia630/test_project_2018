package com.example.beautifulcode.hungryFactory.pay.util;

import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Set;

public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 把继承该类的类成员变量(通过spring 管理的bean) 注入到继承的类中去
     */
    public BeanUtils(){
        Reflections reflections = new Reflections(this.getClass(),new FieldAnnotationsScanner());
        Set<Field> fields = reflections.getFieldsAnnotatedWith(javax.annotation.Resource.class);
        for (Field f: fields){
            try{
                //获得成员变量的类名
                String simpleNamne = f.getType().getSimpleName();
                //因为我们spring里面管理的bean 的name都是首字母不写的
                String beanName = StringUtils.lowerCase(simpleNamne);
                System.out.println("beanName:"+beanName);

                //通过beanname 去applicationcontext去获取 beanname 对象
                Object bean = applicationContext.getBean(simpleNamne);
                if(bean ==null){
                    return;
                }
                //我们必须打破封
                f.setAccessible(true);
                f.set(this,bean);
            }catch (Exception e){

            }

        }
    }

    private static void checkApplicationContext() {
        Assert.notNull(applicationContext,
                "applicationContext未注入,请在applicationContext.xml中定义BeanUtil");
    }

}
