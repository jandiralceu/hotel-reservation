package presentation;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);
    static AdminResource adminResource = AdminResource.getInstance();

    public static void init() {
        while(true) {
            System.out.println("Admin Menu\n" +
                    "--------------------------------------------------------\n" +
                    "1. See all Customers\n" +
                    "2. See all Rooms\n" +
                    "3. See all Reservations\n" +
                    "4. Add a Room\n" +
                    "5. Back to Main Menu\n" +
                    "--------------------------------------------------------\n" +
                    "Please, select a number for the menu option");

            try {
                int menuOptionSelected = scanner.nextInt();

                switch(menuOptionSelected) {
                    case 1:
                        AdminMenu.showAllCustomers();
                        break;
                    case 2:
                        AdminMenu.showAllRooms();
                        break;
                    case 3:
                        AdminMenu.showAllReservations();
                        break;
                    case 4:
                        AdminMenu.addRoom();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("\nPlease, choose one of options bellow");
                }

                if (menuOptionSelected == 5) break;
            } catch (Exception error) {
                System.out.println("\nPlease, choose one of options bellow");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void showAllCustomers() {
        for (Customer customer : adminResource.getAllCustomers()) {
            System.out.println(customer);
        }
    }

    public static void showAllRooms() {
        for (IRoom room : adminResource.getAllRooms()) {
            System.out.println(room);
        }
    }

    public static void showAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void addRoom() {
        List<IRoom> rooms = new ArrayList<IRoom>();

        while(true) {
            String roomNumber = "";
            double pricePerNight = 0.0;
            RoomType roomType = null;

            do {
                System.out.println("Enter room number");

                try {
                    roomNumber = scanner.next();
                } catch (Exception e) {
                    System.out.println("Please, provide a valid room number.");
                } finally {
                    scanner.nextLine();
                }
            } while (roomNumber.isBlank());

            do {
                System.out.println("Enter price per night");

                try {
                    pricePerNight = scanner.nextDouble();
                } catch (Exception e) {
                    System.out.println("Please, provide a valid price.");
                } finally {
                    scanner.nextLine();
                }
            } while (pricePerNight == 0.0);

            do {
                System.out.println("Enter room type: 1 for single bed, 2 for " +
                        "double bed");

                try {
                    int getRoomType = scanner.nextInt();

                    if (getRoomType == 1 || getRoomType == 2) {
                        roomType = getRoomType == 1 ? RoomType.SINGLE :
                                RoomType.DOUBLE;
                    } else {
                       throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    scanner.nextLine();
                }
            } while (roomType == null);

            try {
                System.out.println("Teste");
            } catch (Exception error) {
                System.out.println(error.toString());
            }

            rooms.add(new Room(pricePerNight, roomNumber, roomType));

            System.out.println("\nWould you like to add another room? y/n");
            char answer = scanner.next().toLowerCase().charAt(0);

            if (answer == 'n') {
                adminResource.addRoom(rooms);
                break;
            }
        }
    }
}
