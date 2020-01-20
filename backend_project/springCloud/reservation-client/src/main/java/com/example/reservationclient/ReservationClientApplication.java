package com.example.reservationclient;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.Resources;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@EnableBinding(Source.class)
@EnableHystrix
@EnableCircuitBreaker
@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);
    }

}


/**
 * 访问rest 接口的feign client
 * 声明式的 服务名称 reservation-service 支持url
 * FeignClient 自带负载 ribbon 可以指定策略,
 */
@FeignClient("reservation-service")
interface ReservationReader {
    //Resource 相当 collection对应jpa的列表 微服务返回的是什么,这里就是什么,Bean可以不一样但是属性要一致
    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    Resources<Reservation> read();
}


/**
 * 预定的用户
 */
@RestController
@RequestMapping("/reservations")
@Component
class ReservationApiGatewayRestController {

    private ReservationReader reservationReader;
    private MessageChannel messageChannel;


    public ReservationApiGatewayRestController(ReservationReader reservationReader,
                                               @Qualifier(Source.OUTPUT)MessageChannel messageChannel) {
        this.reservationReader = reservationReader;
        this.messageChannel = messageChannel;
    }

    public Collection<String> fallback() {
        //下面的collections方法出错,会跳到这个方法 如集群/DB出错,可以从缓存里查数据
        //加以加一个参数error 把下面throw 出来的错误捕获到
        System.out.println("fallback hit");
        return Collections.EMPTY_LIST;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(method = RequestMethod.GET, value = "names")
    public Collection<String> read() {
        return this.reservationReader.read().getContent().stream().map(Reservation::getReservationName).collect(Collectors.toList());
    }

    //写入数据
    @RequestMapping(method = RequestMethod.POST, value = "/reservations")
    public void write(@RequestBody  Reservation reservation) {
        messageChannel.send(MessageBuilder.withPayload(reservation.getReservationName()).build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservations2")
    public void write2(@RequestBody Reservation r) {
        Message<String> msg = MessageBuilder.withPayload(r.getReservationName()).build();

    }
}

class Reservation {
    private String reservationName;

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }
}