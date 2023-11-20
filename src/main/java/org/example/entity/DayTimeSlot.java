
package org.example.entity;

import java.util.Objects;

public class DayTimeSlot {

    private String day;
    private TimeSlot timeSlot;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayTimeSlot that = (DayTimeSlot) o;
        return Objects.equals(day, that.day) && Objects.equals(timeSlot, that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, timeSlot);
    }

    @Override
    public String toString() {
        return "DayTimeSlot{" +
                "day='" + day + '\'' +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
