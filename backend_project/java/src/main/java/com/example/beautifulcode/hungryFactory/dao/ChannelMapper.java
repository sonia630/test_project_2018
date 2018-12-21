package com.example.beautifulcode.hungryFactory.dao;

import com.example.beautifulcode.hungryFactory.bean.Channel;
import com.example.beautifulcode.hungryFactory.bean.Goods;

public class ChannelMapper {
    public Channel selectByPrimaryKey(Integer channelId) {
        Channel channel = new Channel();
        return channel;
    }
}
