package cz.itnetwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/greetings") // Tato anotace nastavuje společnou předponu všem akcím v dané třídě -> /greetings/hello-world
public class GreetingsController {

    @GetMapping("/hello-world")
    public String sayHi() { // @ResponseBody upozorňujeme Spring, že chceme vrátit text, jinak by hledal šablonu
        return "hello-world";
    }

}
