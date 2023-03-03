package com.platform.marketplace.Marketplace.Platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/already-registered")
public class AlreadyRegisteredController {

    public String alreadyRegistered(){
        return "alreadyRegistered";
    }
}
