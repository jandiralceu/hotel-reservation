package model;

import java.text.DecimalFormat;

public class Room implements IRoom {
    private final Double price;
    private final String roomNumber;
    private final RoomType enumeration;

    public Room(Double price, String roomNumber, RoomType enumeration) {
        this.price = price;
        this.roomNumber = roomNumber;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(price));
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public String getRoomTypeDescription() {
        return enumeration  == RoomType.SINGLE ? "Single" : "Double";
    }

    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room Number: " + getRoomNumber() +
                "\tRoom Type: " + getRoomTypeDescription() +
                "\tPrice: " + getRoomPrice();
    }
}
