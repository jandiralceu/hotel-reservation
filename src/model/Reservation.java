package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkoutDate;

    @Override
    public String toString() {
        return "Customer: " + customer.fullName();
    }
}
