package com.sirlang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String printHelloWorld() {
        return "Hello World!";
    }

}
