package org.example.service;

import org.example.dao.BasicDao;
import org.example.entity.*;
import org.example.entity.inputJson.BookingJSON;
import org.example.entity.inputJson.MovieInTheatreJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicService {

    @Autowired
    @Qualifier("LocalData")
    private BasicDao basicDao;

    public List<Theatre> getAllTheatres(){
        return basicDao.getAllTheatres();
    }

    public List<Theatre> getAllTheatres(String name){
        return basicDao.getTheatres(name);
    }

    public List<Customer> getAllCustomers(String name){
        return basicDao.getCustomers(name);
    }

    public List<Movie> getAllMovies(String name){
        return basicDao.getMovies(name);
    }

    public Movie createMovie(String name){
        return basicDao.createMovie(name);
    }

    public Theatre createTheatre(String name){
        return basicDao.createTheatre(name);
    }

    public Customer createCustomer(String name){
        return basicDao.createCustomer(name);
    }

    public List<MovieInTheatre> getMovieInTheatre(MovieInTheatreJSON movieInTheatreJSON){
        return basicDao.getMoviesInTheatre(movieInTheatreJSON);
    }

    public MovieInTheatre createMovieInTheatre(MovieInTheatreJSON movieInTheatreJSON){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setFrom(movieInTheatreJSON.getFrom());
        timeSlot.setTo(movieInTheatreJSON.getTo());
        return basicDao.createMovieInTheatre(movieInTheatreJSON.getMovieID(), movieInTheatreJSON.getTheatreID(), movieInTheatreJSON.getDay(), timeSlot);
    }

    public Booking createBooking(BookingJSON bookingJSON){

        return basicDao.creatBooking(bookingJSON);

    }

    public List<Booking> getBookings(String id){
        return basicDao.findBookings(id);
    }

}
