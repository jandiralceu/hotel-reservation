package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkoutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkoutDate = checkoutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.fullName() + "\n" +
                "Room number: " + room.getRoomNumber() + "\n" +
                "Room type: " + (room.getRoomType() == RoomType.SINGLE ?
                "Single" : "Double") + "\n" +
                "Total: $" + room.getRoomPrice() + "\n" +
                "Check-in: " + checkInDate.toString() + "\t" +
                "Check-out: " + checkoutDate.toString() + "\n";
    }
}
