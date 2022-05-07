package presentation;

import api.HotelResource;
import error.InvalidDateException;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    static HotelResource hotelResource = HotelResource.getInstance();

    public static void init() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu\n" +
                    "--------------------------------------------------------\n" +
                    "1. Find and reserve a room\n" +
                    "2. See my reservations\n" +
                    "3. Create an account\n" +
                    "4. Admin\n" +
                    "5. Exit\n" +
                    "--------------------------------------------------------\n" +
                    "Please, select a number for the menu option");

            try {
                int menuOptionSelected = scanner.nextInt();

                switch (menuOptionSelected) {
                    case 1:
                        MainMenu.findAndReserveRoom();
                        break;
                    case 2:
                        MainMenu.getCustomerReservations();
                        break;
                    case 3:
                        MainMenu.createAccount();
                        break;
                    case 4:
                        AdminMenu.init();
                        break;
                    case 5:
                        System.out.println("Closing Hotel Reservation App. " +
                                "Thanks for using!");
                        break;
                    default:
                        System.out.println("\nPlease, choose one of options bellow");
                }

                if (menuOptionSelected == 5) break;
            } catch (Exception error) {
                System.out.println("\nPlease, choose one of options bellow\n");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void createAccount() {
        String firstName = "";
        String lastName = "";
        String email = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Email: eg: name@domain.com");
            email = scanner.nextLine().toLowerCase();
        } while (email.isBlank());

        do {
            System.out.println("First name:");
            firstName = scanner.nextLine();
        } while (firstName.isBlank());

        do {
            System.out.println("Last name:");
            lastName = scanner.nextLine();
        } while (lastName.isBlank());

        try {
            hotelResource.addACustomer(email, firstName, lastName);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }

        System.out.println("");
    }

    public static void findAndReserveRoom() {
        Date checkinDate = null;
        Date checkoutDate = null;
        Scanner scanner = new Scanner(System.in);
        Collection<IRoom> availableRooms = new ArrayList<IRoom>();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        do {
            try {
                System.out.println("Enter checkin date mm/dd/yyyy example " +
                        "05/05/2022:");
                String dateFromUser = scanner.nextLine();
                checkinDate = format.parse(dateFromUser);
            } catch (ParseException error) {
                System.out.println("Date format is invalid. Please follow the" +
                        " instructions.");
            }

        } while (checkinDate == null);

        do {
            try {
                System.out.println("Enter checkout date mm/dd/yyyy example " +
                        "05/13/2022:");
                String dateFromUser = scanner.nextLine();
                checkoutDate = format.parse(dateFromUser);
            } catch (ParseException error) {
                System.out.println("Date format is invalid. Please follow the" +
                        " instructions.");
            }

        } while (checkoutDate == null);

        try {
            availableRooms = hotelResource.findARoom(checkinDate, checkoutDate);

            if (availableRooms.isEmpty() && !hotelResource.getAllRooms().isEmpty()) {
                DateFormat dateFormat = new SimpleDateFormat("MM/dd" +
                        "/yyyy");

                while (availableRooms.isEmpty()) {
                    checkinDate = hotelResource.addRecommendationDays(checkinDate);
                    checkoutDate =
                            hotelResource.addRecommendationDays(checkoutDate);

                    availableRooms = hotelResource.findARoom(checkinDate, checkoutDate);

                    if (!availableRooms.isEmpty()) {
                        System.out.println("\nNew Dates");
                        System.out.println("Checkin: " + dateFormat.format(checkinDate) + "\t" +
                                "Checkout: " + dateFormat.format(checkoutDate));
                    }
                }
            }


        } catch (InvalidDateException  error) {
            System.out.println(error.getMessage());
            System.out.println("");
        }

        if (availableRooms.isEmpty() && hotelResource.getAllRooms().isEmpty()) {
            System.out.println("\nNo rooms available. Try again later" +
                    ".\n");
        }

        if (!availableRooms.isEmpty()) {
            String bookResponse = "";

            System.out.println("\nAvailable Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }
            System.out.println("");

            do {
                System.out.println("Would you like to book a room? y/n");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("n")) {
                    break;
                } else if (response.equalsIgnoreCase("y")) {
                    bookResponse = "y";
                } else {
                    System.out.println("Please, select a valid option.");
                }
            } while (bookResponse.isBlank());

            while (bookResponse.equalsIgnoreCase("y")) {
                String haveAnAccount = "";

                do {
                    System.out.println("Do you have an account with us? y/n");
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("n")) {
                        System.out.println("Please, create an account first" +
                                ".\n");
                        break;
                    } else if (response.equalsIgnoreCase("y")) {
                        haveAnAccount = "y";
                    } else {
                        System.out.println("Please, select a valid option.");
                    }
                } while (haveAnAccount.isBlank());

                if (haveAnAccount.equalsIgnoreCase("y")) {
                    String email = "";
                    Customer customer;

                    do {
                        System.out.println("Enter email: eg. name@domain.com");
                        email = scanner.nextLine().toLowerCase();
                    } while (email.isBlank());

                    customer = hotelResource.getCustomer(email);

                    if (customer == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    String roomNumber = "";
                    IRoom selectedRoom = null;

                    do {
                        System.out.println("What room number would you like " +
                                "to reserve?");
                        String selectedRoomNumber = scanner.nextLine();

                        if (availableRooms.stream().anyMatch(room -> room.getRoomNumber().equals(selectedRoomNumber))) {
                            roomNumber = selectedRoomNumber;
                            selectedRoom = hotelResource.getRoom(roomNumber);
                        } else {
                            System.out.println("Please, provide one of rooms " +
                                    "number listed above.");
                        }
                    } while (roomNumber.isBlank());

                    if (selectedRoom != null) {
                        Reservation reservation =
                                hotelResource.bookARoom(customer,
                                        selectedRoom,
                                        checkinDate, checkoutDate);

                        System.out.println("Reserved");
                        System.out.println(reservation);
                    }
                }

                bookResponse = "";
            }
        }
    }

    public static void getCustomerReservations() {
        Customer customer = null;
        Collection<Reservation> reservations;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter email: eg: name@domain.com");
            String email = scanner.nextLine().toLowerCase();

            if (!email.isBlank()) {
                customer = hotelResource.getCustomer(email);
                break;
            }
        }

        if (customer != null) {
            reservations = hotelResource.getCustomersReservation(customer);

            if (!reservations.isEmpty()) {
                System.out.println("");
                System.out.println("User Reservations:");
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            } else {
                System.out.println("No reservation found.\n");
            }
        } else {
            System.out.println("User not found.\n");
        }
    }
}
