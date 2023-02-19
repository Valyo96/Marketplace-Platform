package com.platform.marketplace.Marketplace.Platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MenuController {

    public String main(){
        return "mainPage";
    }

    public String index(){
        return "index";
    }
}
