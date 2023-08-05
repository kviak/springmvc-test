package ru.kviak.springmvc.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false)  String name){
        System.out.println(name);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
}
