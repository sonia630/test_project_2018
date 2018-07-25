package com.example.girl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class hotdep {

    @RequestMapping(value = "/say",method = RequestMethod.GET)
    public String say(HttpServletRequest request) {
        request.setAttribute("say", "hello imooc, i like imooc");
        return "imoock";
    }
}
