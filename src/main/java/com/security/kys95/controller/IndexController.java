package com.security.kys95.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //return view
public class IndexController {

    @GetMapping({"", "/"})  //localhost:8080/ or localhost:8080
    public String index(){
        return "index";
    }

}
