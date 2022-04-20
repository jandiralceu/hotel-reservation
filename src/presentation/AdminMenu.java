package presentation;

import java.util.Scanner;

public class AdminMenu {
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
        System.out.println("\nAdd a new Room");
    }
}
