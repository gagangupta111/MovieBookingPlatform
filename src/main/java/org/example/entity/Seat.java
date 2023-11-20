package org.example.entity;

public class Seat {

    private boolean booked;
    private String ID;
    private Customer customer;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "booked=" + booked +
                ", ID='" + ID + '\'' +
                ", customer=" + customer +
                '}';
    }
}
