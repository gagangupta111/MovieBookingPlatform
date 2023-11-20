package org.example.service;

import org.example.dao.BasicDao;
import org.example.entity.*;
import org.example.entity.inputJson.MovieInTheatreJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public List<MovieInTheatre> getMovieInTheatre(String id, String type){
        return basicDao.getMoviesInTheatre(id, type);
    }

    public MovieInTheatre createMovieInTheatre(MovieInTheatreJSON movieInTheatreJSON){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setFrom(movieInTheatreJSON.getFrom());
        timeSlot.setTo(movieInTheatreJSON.getTo());
        return basicDao.createMovieInTheatre(movieInTheatreJSON.getMovieID(), movieInTheatreJSON.getTheatreID(), movieInTheatreJSON.getDay(), timeSlot);
    }

    public Booking createBooking(String movieID, String theatreID, String day, Integer from, Integer to, String seat, String customerID){

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setFrom(from);
        timeSlot.setTo(to);

        List<Booking> list = basicDao.findBookings(movieID, theatreID, day, timeSlot, seat, customerID);
        if (!list.isEmpty()){
            return null;
        }

        Booking booking = new Booking();

        Customer customer = basicDao.getCustomers(customerID).get(0);

        booking.setCustomer(customer);
        booking.setMovie(basicDao.getMovies(movieID).get(0));
        booking.setTheatre(basicDao.getTheatres(theatreID).get(0));

        Seat seat1 = new Seat();
        seat1.setCustomer(customer);
        seat1.setID(seat);
        booking.setSeat(seat1);

        return booking;

    }

}
