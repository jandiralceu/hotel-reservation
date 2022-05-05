package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Reservation)) {
            return false;
        }

        Reservation reservation = (Reservation) obj;

        return this.room.equals(reservation.room) &&
                this.customer.equals(reservation.customer) &&
                this.checkInDate.equals(reservation.checkInDate) &&
                this.checkoutDate.equals(reservation.checkoutDate);
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        return "Customer: " + customer.fullName() + "\n" +
                "Room number: " + room.getRoomNumber() + "\n" +
                "Room type: " + (room.getRoomType() == RoomType.SINGLE ?
                "Single" : "Double") + " bed\n" +
                "Price: $" + decimalFormat.format(room.getRoomPrice()) + " per night\n" +
                "Check-in: " + dateFormat.format(checkInDate) + "\t" +
                "Check-out: " + dateFormat.format(checkoutDate) + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkoutDate);
    }
}
