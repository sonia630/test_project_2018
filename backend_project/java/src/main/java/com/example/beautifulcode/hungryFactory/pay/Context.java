package com.example.beautifulcode.hungryFactory.pay;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 上下文
 */

public class Context {
    private Strategry strategry;

    public BigDecimal calRecharge(Integer channlId, Integer goodsId) throws Exception {
        //根据自己的type, 把实现类给我返回回来
        //利用反射 把对象创建出来 工厂生产出来 ,返回一个strate

        //1.先创建一个factory
        StrategryFactory strategryFactory = StrategryFactory.getInstance();
        //2.利用factory 返回一个strategry 调用strategryFactory里面的方法返回一个strategry
        strategry = strategryFactory.create(channlId);
        return strategry.calRecharge(channlId,goodsId);
    }


}
