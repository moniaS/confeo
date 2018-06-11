package com.example.confeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
@Controller
public class HomeController extends BasicController{

    @RequestMapping(value = "")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/about-us")
    public String aboutUs(){
    	return "about-us";
    }
}
