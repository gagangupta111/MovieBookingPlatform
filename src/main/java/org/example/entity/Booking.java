package org.example.entity;

public class Booking {

    private String ID;
    private Movie movie;
    private Theatre theatre;
    private Customer customer;
    private Seat seat;
    private DayTimeSlot dayTimeSlot;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public DayTimeSlot getDayTimeSlot() {
        return dayTimeSlot;
    }

    public void setDayTimeSlot(DayTimeSlot dayTimeSlot) {
        this.dayTimeSlot = dayTimeSlot;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "ID='" + ID + '\'' +
                ", movie=" + movie +
                ", theatre=" + theatre +
                ", customer=" + customer +
                ", seat=" + seat +
                ", dayTimeSlot=" + dayTimeSlot +
                '}';
    }
}
