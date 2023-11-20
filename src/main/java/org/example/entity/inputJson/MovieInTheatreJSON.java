package org.example.entity.inputJson;

import org.example.entity.Seat;

import java.util.List;

public class MovieInTheatreJSON {

    private String ID;
    private String movieID;
    private String theatreID;
    private String day;
    private int from;
    private int to;

    private List<Seat> seats;

    public MovieInTheatreJSON() {
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

    @Override
    public String toString() {
        return "MovieInTheatreJSON{" +
                "ID='" + ID + '\'' +
                ", movieID='" + movieID + '\'' +
                ", theatreID='" + theatreID + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", seats=" + seats +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
