package org.example.service;

import org.example.dao.BasicDao;
import org.example.entity.*;
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
