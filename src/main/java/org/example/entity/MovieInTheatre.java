package org.example.entity;

import java.util.List;

public class MovieInTheatre {

    private String ID;
    private Movie movie;
    private Theatre theatre;

    private List<DayTimeSlot> dayTimeSlots;

    private List<Seat> seats;

    public List<DayTimeSlot> getDayTimeSlots() {
        return dayTimeSlots;
    }

    public void setDayTimeSlots(List<DayTimeSlot> dayTimeSlots) {
        this.dayTimeSlots = dayTimeSlots;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }


    @Override
    public String toString() {
        return "MovieInTheatre{" +
                "ID='" + ID + '\'' +
                ", movie=" + movie +
                ", theatre=" + theatre +
                ", dayTimeSlots=" + dayTimeSlots +
                ", seats=" + seats +
                '}';
    }


}
