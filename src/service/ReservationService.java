package service;

import error.InvalidDateException;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService instance;
    private final List<Reservation> reservations = new ArrayList<Reservation>();
    private final Map<String, IRoom> rooms = new HashMap<String, IRoom>();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public List<IRoom> getAllRooms() {
        return new ArrayList<IRoom>(rooms.values());
    }

    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate
                , checkOutDate);
        reservations.add(reservation);

        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) throws InvalidDateException {
        if (checkOutDate.before(checkInDate) || checkInDate.equals(checkOutDate)) {
            throw new InvalidDateException("Check-out must be after check-in.");
        }

        if (checkInDate.before(new Date())) {
            throw new InvalidDateException("Please, provide present and " +
                    "future date");
        }

        List<String> unAvailableRooms = new ArrayList<String>();

        for (Reservation reservation : reservations) {
            if (checkInDate.compareTo(reservation.getCheckInDate()) >= 0 && checkInDate.compareTo(reservation.getCheckoutDate()) <= 0 ||
                    checkOutDate.compareTo(reservation.getCheckInDate()) >= 0 && checkOutDate.compareTo(reservation.getCheckoutDate()) <= 0) {
                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
            }
        }

        Map<String, IRoom> availableRooms = new HashMap<>(rooms);

        for (String room : unAvailableRooms) {
            availableRooms.remove(room);
        }

        return new ArrayList<IRoom>(availableRooms.values());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> userReservation = new ArrayList<Reservation>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                userReservation.add(reservation);
            }
        }
        return userReservation;
    }

    public void printAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Sorry, there is no reservation. Go to main " +
                    "menu and start creating.");
        } else {
            System.out.println("Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }

        System.out.println("");
    }
}
