package com.saferize.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aman Mahato
 */

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String home(){
        return "webpage";
    }

}
