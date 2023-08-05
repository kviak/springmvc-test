package ru.kviak.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false)  String name, Model model){
        model.addAttribute("message", name);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calc(@RequestParam("a") int a, @RequestParam("b") int b,
                       @RequestParam("action") String action, Model model){

        int answer;

        switch (action) {
            case "plus" -> answer = a + b;
            case "minus" -> answer = a - b;
            case "multip" -> answer = a * b;
            case "divis" -> answer = a / b;
            default -> answer=0;
        }
        model.addAttribute("answer", answer);
        return "first/calculator";
    }
}
