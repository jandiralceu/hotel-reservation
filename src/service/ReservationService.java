package service;

import error.InvalidDateException;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String formattedCheckinDate = dateFormatter.format(checkInDate);

        Date date = new Date();
        String currentDate = dateFormatter.format(date);

        if (checkInDate.compareTo(date) < 0 && !currentDate.equals(formattedCheckinDate)) {
            throw new InvalidDateException("Please, check-in date can't be " +
                    "a date before today.");
        }

        if (reservations.isEmpty() && !rooms.isEmpty()) {
            return new ArrayList<IRoom>(rooms.values());
        }

        Set<String> unAvailableRooms = new HashSet<String>();

        for (Reservation reservation : reservations) {
            /* Check if provided checkin and checkout are inside created
            reservations. */
            if (checkInDate.compareTo(reservation.getCheckInDate()) >= 0 && checkInDate.compareTo(reservation.getCheckoutDate()) <= 0) {
                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
                continue;
            }

            if (checkOutDate.compareTo(reservation.getCheckInDate()) >= 0 && checkOutDate.compareTo(reservation.getCheckoutDate()) <= 0) {
                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
                continue;
            }

            /* Check if provided checkin and checkout don't overflow created
            reservations. */
            if (reservation.getCheckInDate().compareTo(checkInDate) >= 0 && reservation.getCheckInDate().compareTo(checkOutDate) <= 0) {
                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
                continue;
            }
            if (reservation.getCheckoutDate().compareTo(checkInDate) >= 0 && reservation.getCheckoutDate().compareTo(checkOutDate) <= 0) {
                unAvailableRooms.add(reservation.getRoom().getRoomNumber());
            }
        }

        Map<String, IRoom> availableRooms = new HashMap<>(rooms);

        for (String room : unAvailableRooms) {
            availableRooms.remove(room);
        }

        if (availableRooms.isEmpty() && !reservations.isEmpty()) {
            System.out.println("Suggestions");
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
            System.out.println("");
            System.out.println("Sorry, there is no reservation. Go to main " +
                    "menu and start creating.");
            System.out.println("");
        } else {
            System.out.println("Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }
}
