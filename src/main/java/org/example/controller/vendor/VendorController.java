package org.example.controller.vendor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    @RequestMapping("/vendors")
    String hello() {
        return "Hello World, Spring Boot!";
    }

}