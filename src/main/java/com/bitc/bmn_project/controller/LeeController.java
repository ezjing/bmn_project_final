package com.bitc.bmn_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bmn")
public class LeeController {

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }
}
