package model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Room implements IRoom {
    private final Double price;
    private final String roomNumber;
    private final RoomType enumeration;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Room(Double price, String roomNumber, RoomType enumeration) {
        this.price = price;
        this.roomNumber = roomNumber;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public String getRoomTypeDescription() {
        return enumeration == RoomType.SINGLE ? "Single bed" : "Double bed";
    }

    public boolean isFree() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Room)) {
            return false;
        }

        Room room = (Room) obj;

        return this.roomNumber.equals(room.roomNumber) &&
                this.price.equals(room.price) &&
                this.enumeration == room.enumeration;
    }

    @Override
    public String toString() {
        return "Room Number: " + getRoomNumber() +
                "\tRoom Type: " + getRoomTypeDescription() +
                "\tPrice: $" + decimalFormat.format(price) + " per night";
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, roomNumber, enumeration, decimalFormat);
    }
}
