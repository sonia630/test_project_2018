package com.example.beautifulcode.hungryFactory.pay.impl;

import com.example.beautifulcode.hungryFactory.bean.Channel;
import com.example.beautifulcode.hungryFactory.bean.Goods;
import com.example.beautifulcode.hungryFactory.dao.ChannelMapper;
import com.example.beautifulcode.hungryFactory.dao.GoodsMapper;
import com.example.beautifulcode.hungryFactory.pay.Context;
import com.example.beautifulcode.hungryFactory.pay.Pay;
import com.example.beautifulcode.hungryFactory.pay.Strategry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 注入dao的时候用new的时候 @Resource 注入的时候为空
 * GoodsMapper,ChannelMapper 不受spring控制,手动的获取bean, ApplicationContext
 * 需要 实现ApplicationContext,传到实现的类里面,先从ApplicationContext拿到bean然后 set回去
 */
@Pay(channelId = 1)
public class ICBCPay extends BeanUtils implements Strategry {

    //1. 注入dao
    @Resource
    @Autowired
    private ChannelMapper channelMapper;

    @Resource
    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * 工商银行具体实现类
     * 用于 BigDecimal, doblue 容易精度丢失
     * @param channlId
     * @param goodsId
     * @return
     */
    @Override
    public BigDecimal calRecharge(Integer channlId, Integer goodsId) {

        Channel channel = channelMapper.selectByPrimaryKey(channlId);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);

        if(channel != null && goods != null){
            //BigDecimal 相乘 mutiply()
           return goods.getAmout().multiply(channel.getDisCount());
        }else

        return null;
    }

}
