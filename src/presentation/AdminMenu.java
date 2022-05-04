package presentation;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    static AdminResource adminResource = AdminResource.getInstance();

    public static void init() {
        Scanner scanner = new Scanner(System.in);

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
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("Sorry, there is o customers. Go to main menu " +
                    "and start to creating.\n");
        } else {
            System.out.println("\nCustomers");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            System.out.println("\n");
        }
    }

    public static void showAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("Sorry, there is no rooms. Select 4 option " +
                    "and start to creating\n");
        } else {
            System.out.println("\nRooms");
            for (IRoom room : rooms) {
                System.out.println(room);
            }
            System.out.println("");
        }
    }

    public static void showAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void addRoom() {
        String answer = "";
        List<IRoom> rooms = new ArrayList<IRoom>();
        Scanner scanner = new Scanner(System.in);

        do {
            String roomNumber = "";
            double pricePerNight = 0.0;
            RoomType roomType = null;

            do {
                System.out.println("Enter room number:");

                try {
                    String tempRoomNumber = scanner.next();

                    if (!tempRoomNumber.isEmpty()) {
                        Integer.parseInt(tempRoomNumber);
                        roomNumber = tempRoomNumber;
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Please, provide a valid room number: " +
                            "eg: 201");
                } finally {
                    scanner.nextLine();
                }
            } while (roomNumber.isBlank());

            do {
                System.out.println("Enter price per night:");

                try {
                    double tempPriceNight = scanner.nextDouble();

                    if (tempPriceNight <= 0) {
                        throw new NumberFormatException("Please, provide a " +
                                "price greater than 0.0");
                    } else {
                        pricePerNight = tempPriceNight;
                    }
                } catch (NumberFormatException | InputMismatchException error) {
                    if (error instanceof  InputMismatchException) {
                        System.out.println("Please, provide a valid price. " +
                                "eg: 120");
                    }

                    if (error instanceof NumberFormatException) {
                        System.out.println(((NumberFormatException) error).getMessage());
                    }
                } finally {
                    scanner.nextLine();
                }
            } while (pricePerNight <= 0);

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
                } catch (Exception error) {
                    System.out.println("Please, enter the correct room " +
                            "type.");
                } finally {
                    scanner.nextLine();
                }
            } while (roomType == null);

            rooms.add(new Room(pricePerNight, roomNumber, roomType));

            do {
                System.out.println("\nWould you like to add another room? y/n");
                String response = scanner.nextLine().toLowerCase();

                if (response.equals("n")) {
                    adminResource.addRoom(rooms);
                    answer = response;
                    break;
                } else if (response.equals("y")) {
                    answer = response;
                } else {
                    System.out.println("Please, select a valid option: y/n");
                }
            } while (answer.isBlank());
        } while (!answer.equals("n"));
    }
}
