package org.example.dao;

import org.example.entity.*;
import org.example.entity.inputJson.BookingJSON;
import org.example.entity.inputJson.MovieInTheatreJSON;

import java.util.List;

public interface BasicDao {

    public List<Customer> getCustomers(String name);
    public List<Theatre> getTheatres(String name);
    public List<Movie> getMovies(String name);
    public List<MovieInTheatre> getMoviesInTheatre(MovieInTheatreJSON movieInTheatreJSON);
    public Customer createCustomer(String name);
    public Theatre createTheatre(String name);
    public Movie createMovie(String name);
    public MovieInTheatre createMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot);
    public List<Booking> findBookings(String movieID, String theatreID, String day, TimeSlot timeSlot, String customerID);
    public List<Booking> findBookings(String customerID);
    public List<MovieInTheatre> findMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot);
    public List<Theatre> getTheatres();
    public void setTheatres(List<Theatre> theatres);
    public List<Theatre> getAllTheatres();
    public Booking creatBooking(BookingJSON bookingJSON);


}
