package org.example.dao;

import org.example.entity.*;
import org.example.util.Basic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface BasicDao {

    public List<Customer> getCustomers(String name);
    public List<Theatre> getTheatres(String name);
    public List<Movie> getMovies(String name);
    public Customer createCustomer(String name);
    public Theatre createTheatre(String name);
    public Movie createMovie(String name);
    public MovieInTheatre createMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot);
    public List<Booking> findBookings(String movieID, String theatreID, String day, TimeSlot timeSlot, String seat, String customerID);
    public List<MovieInTheatre> findMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot);
    public List<Theatre> getTheatres();
    public void setTheatres(List<Theatre> theatres);
    public List<Theatre> getAllTheatres();

}