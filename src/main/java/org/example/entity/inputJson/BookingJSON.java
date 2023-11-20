package org.example.entity.inputJson;

public class BookingJSON {

    private String ID;
    private String movieID;
    private String theatreID;
    private String customerID;
    private int from;
    private int to;
    private String seat;

    public BookingJSON() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(String theatreID) {
        this.theatreID = theatreID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "BookingJSON{" +
                "ID='" + ID + '\'' +
                ", movieID='" + movieID + '\'' +
                ", theatreID='" + theatreID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", seat='" + seat + '\'' +
                '}';
    }
}
