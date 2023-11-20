package org.example.dao;

import org.example.entity.*;
import org.example.util.Basic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("LocalData")
public class BasicDaoImpl implements BasicDao {

    private List<Theatre> theatres;
    private List<Movie> movies;
    private List<Customer> customers;
    private List<MovieInTheatre> movieInTheatres;
    private List<Booking> bookings;

    public List<Customer> getCustomers(String name) {
        List<Customer> customerList = customers.stream().filter((a) -> a.getName().equals(name)).collect(Collectors.toList());
        if (customerList.isEmpty()){
            customerList = customers.stream().filter((a) -> a.getID().equals(name)).collect(Collectors.toList());
        }

        return customerList;
    }

    public List<Theatre> getTheatres(String name) {

        List<Theatre> customerList = theatres.stream().filter((a) -> a.getName().equals(name)).collect(Collectors.toList());
        if (customerList.isEmpty()){
            customerList = theatres.stream().filter((a) -> a.getID().equals(name)).collect(Collectors.toList());
        }

        return customerList;
    }

    public List<Movie> getMovies(String name) {

        List<Movie> customerList = movies.stream().filter((a) -> a.getName().equals(name)).collect(Collectors.toList());
        if (customerList.isEmpty()){
            customerList = movies.stream().filter((a) -> a.getID().equals(name)).collect(Collectors.toList());
        }

        return customerList;
    }

    public Customer createCustomer(String name) {

        List<Customer> customerList = getCustomers(name);
        if (!customerList.isEmpty()){
            return null;
        }

        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setID("" + Basic.getRandom());
        return newCustomer;
    }

    public Theatre createTheatre(String name) {

        List<Theatre> theatreList = getTheatres(name);
        if (!theatreList.isEmpty()){
            return null;
        }

        Theatre newTheatre = new Theatre();
        newTheatre.setName(name);
        newTheatre.setID("" + Basic.getRandom());
        return newTheatre;
    }

    public Movie createMovie(String name) {

        List<Movie> movieList = getMovies(name);
        if (!movieList.isEmpty()){
            return null;
        }

        Movie newMovie = new Movie();
        newMovie.setName(name);
        newMovie.setID("" + Basic.getRandom());
        return newMovie;
    }

    public MovieInTheatre createMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot) {

        List<MovieInTheatre> list = findMovieInTheatre(movieID, theatreID, day, timeSlot);

        if (!list.isEmpty()){
            for (MovieInTheatre movieInTheatre : list){
                for (DayTimeSlot dayTimeSlot : movieInTheatre.getDayTimeSlots()){
                    if (dayTimeSlot.getDay().equals(day) && dayTimeSlot.getTimeSlot().equals(timeSlot)){
                        return null;
                    }
                }
            }
        }

        MovieInTheatre movieInTheatre = new MovieInTheatre();
        movieInTheatre.setMovie(getMovies(movieID).get(0));
        movieInTheatre.setTheatre(getTheatres(theatreID).get(0));

        DayTimeSlot dayTimeSlot = new DayTimeSlot();
        dayTimeSlot.setDay(day);
        dayTimeSlot.setTimeSlot(timeSlot);
        List<DayTimeSlot> list1 = new ArrayList<>();
        list1.add(dayTimeSlot);

        movieInTheatre.setDayTimeSlots(list1);
        return movieInTheatre;
    }

    public List<Booking> findBookings(String movieID, String theatreID, String day, TimeSlot timeSlot, String seat, String customerID) {

        return bookings.stream()
                .filter(a -> a.getMovie().getID().equals(movieID))
                .filter(a -> a.getTheatre().getID().equals(theatreID))
                .filter(a -> a.getDayTimeSlot().getDay().equals(day))
                .filter(a -> a.getDayTimeSlot().getTimeSlot().equals(timeSlot))
                .filter(a -> a.getSeat().getID().equals(seat))
                .filter(a -> a.getSeat().getCustomer().getID().equals(customerID))
                .collect(Collectors.toList());
    }

    public List<MovieInTheatre> findMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot) {

        return movieInTheatres.stream().
                filter((a) -> a.getMovie().getID().equals(movieID)).
                filter((a) -> a.getTheatre().getID().equals(theatreID)).collect(Collectors.toList());
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatres;
    }
}
