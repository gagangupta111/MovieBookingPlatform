package org.example.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @RequestMapping("/customers")
    String hello() {
        return "Hello World, Spring Boot!";
    }

}