package com.example.beautifulcode.hungryFactory;

import com.example.beautifulcode.hungryFactory.pay.Context;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping(value = {"/channelId/{channelId}"},method = RequestMethod.GET)
    public void test(@PathVariable("channelId") int channelId) throws Exception {
        Context context = new Context();
        context.calRecharge(channelId,1);
    }
}
