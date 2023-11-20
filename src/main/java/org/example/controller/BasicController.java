
package org.example.controller;

import org.example.entity.Customer;
import org.example.entity.Movie;
import org.example.entity.MovieInTheatre;
import org.example.entity.Theatre;
import org.example.entity.inputJson.BookingJSON;
import org.example.entity.inputJson.MovieInTheatreJSON;
import org.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class BasicController {

    @Autowired
    private BasicService basicService;

    @RequestMapping("/hello")
    String hello() {
        return "Hello World, Spring Boot!";
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Customer createCustomer(@RequestBody Customer customer) {
        return basicService.createCustomer(customer.getName());
    }

    @RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET)
    List<Customer> getCustomer(@PathVariable("id") String id) {

        return basicService.getAllCustomers(id);
    }

    @RequestMapping(value = "/createTheatre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Theatre createTheatre(@RequestBody Theatre theatre) {
        return basicService.createTheatre(theatre.getName());
    }

    @RequestMapping(value = "/getTheatre/{id}", method = RequestMethod.GET)
    List<Theatre> getTheatre(@PathVariable("id") String id) {

        return basicService.getAllTheatres(id);
    }

    @RequestMapping(value = "/createMovie", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Movie createMovie(@RequestBody Movie movie) {
        return basicService.createMovie(movie.getName());
    }

    @RequestMapping(value = "/getMovie/{id}", method = RequestMethod.GET)
    List<Movie> getMovie(@PathVariable("id") String id) {

        return basicService.getAllMovies(id);
    }

    @RequestMapping(value = "/createMovieInTheatre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    MovieInTheatre createMovieInTheatre(@RequestBody MovieInTheatreJSON movieInTheatreJSON) {
        return basicService.createMovieInTheatre(movieInTheatreJSON);
    }

    @RequestMapping(value = "/getMovieInTheatre/{id}/{type}", method = RequestMethod.GET)
    List<MovieInTheatre> getMovieInTheatre(@PathVariable("id") String id, @PathVariable("type") String type) {

        return basicService.getMovieInTheatre(id, type);
    }

    @RequestMapping(value = "/createBooking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    String createBooking(@RequestBody BookingJSON bookingJSON) {
        System.out.println(bookingJSON);
        return "";
    }
}
