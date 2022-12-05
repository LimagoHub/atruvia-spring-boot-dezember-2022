package de.atruvia.mywebapp.presentation;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/gruss")
    public String gruss() {
        return "Hallo Rest";
    }

    @GetMapping("/uuid")
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
