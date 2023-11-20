
package org.example.controller;

import org.example.entity.Theatre;
import org.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class BasicController {

    @Autowired
    private BasicService basicService;

    @RequestMapping("/all")
    List<Theatre> getAllTheatres() {
        return basicService.getAllTheatres();
    }

    @RequestMapping("/hello")
    String hello() {
        return "Hello World, Spring Boot!";
    }

    @RequestMapping("/createBooking")
    String createBooking() {
        return "";
    }

}
