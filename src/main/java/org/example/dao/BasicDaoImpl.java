package org.example.dao;

import org.example.entity.*;
import org.example.entity.inputJson.BookingJSON;
import org.example.entity.inputJson.MovieInTheatreJSON;
import org.example.util.Basic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("LocalData")
public class BasicDaoImpl implements BasicDao {

    private List<Theatre> theatres = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<MovieInTheatre> movieInTheatres = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

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
        customers.add(newCustomer);
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
        theatres.add(newTheatre);
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
        movies.add(newMovie);
        return newMovie;
    }

    public MovieInTheatre createMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot) {

        List<MovieInTheatre> list = findMovieInTheatre(movieID, theatreID, day, timeSlot);

        if (!list.isEmpty()){
            return null;
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
        List<Seat> seats = createSeats(5);
        movieInTheatre.setSeats(seats);
        movieInTheatres.add(movieInTheatre);
        return movieInTheatre;
    }

    public List<Seat> createSeats(int size){
        List<Seat> seats = new ArrayList<>(5);
        for (int i = 0 ; i < size; i++){
            seats.add(new Seat());
        }
        return seats;
    }

    @Override
    public Booking creatBooking(BookingJSON bookingJSON){

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setFrom(bookingJSON.getFrom());
        timeSlot.setTo(bookingJSON.getTo());

        List<Booking> list = findBookings(bookingJSON.getMovieID(),
                bookingJSON.getTheatreID(), bookingJSON.getDay(), timeSlot, bookingJSON.getCustomerID());

        if (!list.isEmpty()){
            return null;
        }

        List<MovieInTheatre> movieInTheatreList = findMovieInTheatre(bookingJSON.getMovieID(),
                bookingJSON.getTheatreID(), bookingJSON.getDay(), timeSlot );

        if (movieInTheatreList.isEmpty()){
            return null;
        }

        MovieInTheatre movieInTheatre = movieInTheatreList.get(0);
        Seat seat = getEmptySeat(movieInTheatre, Integer.parseInt(bookingJSON.getSeat()));

        if (seat == null){
            seat = getEmptySeat(movieInTheatre);
            if (seat == null){
                return null;
            }
        }

        seat.setBooked(true);
        seat.setCustomer(getCustomers(bookingJSON.getCustomerID()).get(0));

        Booking booking = new Booking();
        booking.setCustomer(getCustomers(bookingJSON.getCustomerID()).get(0));
        booking.setTheatre(movieInTheatre.getTheatre());
        booking.setMovie(movieInTheatre.getMovie());

        DayTimeSlot dayTimeSlot = new DayTimeSlot();
        dayTimeSlot.setDay(bookingJSON.getDay());
        dayTimeSlot.setTimeSlot(timeSlot);

        booking.setDayTimeSlot(dayTimeSlot);
        booking.setSeat(seat);
        bookings.add(booking);
        return booking;

    }

    public Seat getEmptySeat(MovieInTheatre movieInTheatre){
        for (Seat seat : movieInTheatre.getSeats()){
            if (!seat.isBooked()){
                return seat;
            }
        }
        return null;
    }

    public Seat getEmptySeat(MovieInTheatre movieInTheatre, int seatNumber){

        seatNumber--;
        Seat seat = movieInTheatre.getSeats().get(seatNumber);
        if (!seat.isBooked()){
            return seat;
        }
        return null;
    }

    public List<Booking> findBookings(String movieID, String theatreID, String day, TimeSlot timeSlot, String customerID) {

        return bookings.stream()
                .filter(a -> a.getMovie().getID().equals(movieID) || a.getMovie().getName().equals(movieID) )
                .filter(a -> a.getTheatre().getID().equals(theatreID) || a.getTheatre().getName().equals(theatreID))
                .filter(a -> a.getDayTimeSlot().getDay().equals(day))
                .filter(a -> a.getDayTimeSlot().getTimeSlot().equals(timeSlot))
                .filter(a -> a.getSeat().getCustomer().getID().equals(customerID) || a.getSeat().getCustomer().getName().equals(customerID))
                .collect(Collectors.toList());
    }

    public List<Booking> findBookings(String customerID) {

        return bookings.stream()
                .filter(a -> a.getSeat().getCustomer().getID().equals(customerID)
                || a.getSeat().getCustomer().getName().equals(customerID))
                .collect(Collectors.toList());
    }

    public List<MovieInTheatre> getMoviesInTheatre(MovieInTheatreJSON movieInTheatreJSON) {

        List<MovieInTheatre> list = movieInTheatres;
        if (movieInTheatreJSON.getMovieID() != null){
            list = list.stream()
                    .filter((a) -> a.getMovie().getID().equals(movieInTheatreJSON.getMovieID())
                            || a.getMovie().getName().equals(movieInTheatreJSON.getMovieID()))
                    .collect(Collectors.toList());
        }

        if (movieInTheatreJSON.getTheatreID() != null){
            list = list.stream()
                    .filter((a) -> a.getTheatre().getID().equals(movieInTheatreJSON.getTheatreID())
                            || a.getTheatre().getName().equals(movieInTheatreJSON.getTheatreID()))
                    .collect(Collectors.toList());
        }

        if (movieInTheatreJSON.getDay() != null){
            List<MovieInTheatre> returnList = new ArrayList<>();
            if (movieInTheatreJSON.getDay() != null){
                for (MovieInTheatre movieInTheatre : list){
                    for (DayTimeSlot dayTimeSlot : movieInTheatre.getDayTimeSlots()){
                        if (dayTimeSlot.getDay().equals(movieInTheatreJSON.getDay())){
                            returnList.add(movieInTheatre);
                        }
                    }
                }
            }
            list = returnList;
        }

        return list;
    }

    public List<MovieInTheatre> findMovieInTheatre(String movieID, String theatreID, String day, TimeSlot timeSlot) {

        List<MovieInTheatre> list = movieInTheatres.stream().
                filter((a) -> a.getMovie().getID().equals(movieID) || a.getMovie().getName().equals(movieID)).
                filter((a) -> a.getTheatre().getID().equals(theatreID) || a.getTheatre().getName().equals(theatreID))
                .collect(Collectors.toList());

        List<MovieInTheatre> returnList = new ArrayList<>();
        for (MovieInTheatre movieInTheatre : list){
            for (DayTimeSlot dayTimeSlot : movieInTheatre.getDayTimeSlots()){
                if (dayTimeSlot.getDay().equals(day) && dayTimeSlot.getTimeSlot().equals(timeSlot)){
                    returnList.add(movieInTheatre);
                }
            }
        }
        return returnList;
    }

    public List<MovieInTheatre> findMovieInTheatre(String movieID, String theatreID) {

        return movieInTheatres.stream().
                filter((a) -> a.getMovie().getID().equals(movieID) || a.getMovie().getName().equals(movieID)).
                filter((a) -> a.getTheatre().getID().equals(theatreID) || a.getTheatre().getName().equals(theatreID))
                .collect(Collectors.toList());
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
