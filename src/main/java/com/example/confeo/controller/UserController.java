package com.example.confeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
@Controller
public class UserController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
