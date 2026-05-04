package com.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserTestController {

    @GetMapping("/data")
    public String userData()
    {
        return "User or Admin can Access this";
    }
}
