package com.saferize.interview.controller;

import com.saferize.interview.dto.UrlDto;
import com.saferize.interview.services.FindUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aman Mahato
 */

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String home(Model model){
        model.addAttribute("urlDto",new UrlDto());
        return "webpage";
    }
}
