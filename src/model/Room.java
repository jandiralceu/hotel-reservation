package model;

public class Room implements IRoom {
    protected Double price;
    private String roomNumber;
    private RoomType enumeration;

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
