package org.example.vendor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Vendor {

    @RequestMapping("/")
    String hello() {
        return "Hello World, Spring Boot!";
    }

}