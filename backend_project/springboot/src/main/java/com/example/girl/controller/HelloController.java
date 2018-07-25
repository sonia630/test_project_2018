package com.example.girl.controller;

import com.example.girl.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@RestController
//@RequestMapping("hello")
public class HelloController {
    @Value("${cupSize}")
    private String cupSie;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/{id}/say", method = RequestMethod.GET)
//    @GetMapping(value="/say")
    public String say(@PathVariable("id") Integer id,
                      @RequestParam(value = "name",required = false,defaultValue = "0")  String name
    ) {

        return girlProperties.getName();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request,@RequestParam(value = "name",required = false,defaultValue = "0")  String name
    ) {
        request.setAttribute("hello","hello imooc, aaa");
        return "say hello 666";

    }

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        Period period = Period.between(date2,date1);
        System.out.printf("%s%n%s%n%s", date1, date2,period);

    }
}
