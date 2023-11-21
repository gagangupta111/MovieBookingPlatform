
package org.example.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.*;
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
    Response createMovieInTheatre(@RequestBody MovieInTheatreJSON movieInTheatreJSON) throws JsonProcessingException {
        MovieInTheatre movieInTheatre = basicService.createMovieInTheatre(movieInTheatreJSON);
        Response response = new Response();
        if (movieInTheatre == null){
            response.setSuccess(false);
            response.setMessage("Already exists");
        }else {
            response.setSuccess(true);
            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(movieInTheatre);
            response.setMessage(jsonStr);
        }

        return response;

    }

    @RequestMapping(value = "/getMovieInTheatre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<MovieInTheatre> getMovieInTheatre(@RequestBody MovieInTheatreJSON movieInTheatreJSON) throws JsonProcessingException {
        return basicService.getMovieInTheatre(movieInTheatreJSON);
    }

    @RequestMapping(value = "/createBooking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Booking createBooking(@RequestBody BookingJSON bookingJSON) {

        return basicService.createBooking(bookingJSON);
    }

    @RequestMapping(value = "/getBookings/{cust_id}", method = RequestMethod.GET)
    List<Booking> getBookings(@PathVariable("cust_id") String id) {

        return basicService.getBookings(id);
    }

}
