package ru.kviak.springmvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/goida")
    public String mess(){
        return "goida";
    }

}
