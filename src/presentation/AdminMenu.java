package presentation;

import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

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

    public static void showAllRooms() {
        System.out.println("\nShow all Rooms");
    }

    public static void showAllCustomers() {
        System.out.println("\nShow all customers");
    }

    public static void showAllReservations() {
        System.out.println("\nShow All Reservations");
    }

    public static void addRoom() {
        while(true) {
            int roomNumber = 0;
            double pricePerNight = 0.0;
            int roomType = 0;

            do {
                System.out.println("Enter room number");

                try {
                    roomNumber = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Please, provide a valid room number.");
                } finally {
                    scanner.nextLine();
                }
            } while (roomNumber == 0);

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
                        roomType = getRoomType;
                    } else {
                       throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Please, provide a valid room type.");
                } finally {
                    scanner.nextLine();
                }
            } while (roomType == 0);



            System.out.println("\nWould you like to add another room? y/n");
            char answer = scanner.next().toLowerCase().charAt(0);

            if (answer == 'n') break;
        }
    }
}
