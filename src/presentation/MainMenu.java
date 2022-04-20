package presentation;

import java.util.Scanner;

public class MainMenu {
    public static void init() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
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

                switch(menuOptionSelected) {
                    case 1:
                        MainMenu.findAndReserveRoom();
                        break;
                    case 2:
                        MainMenu.showMyReservations();
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
                System.out.println("\nPlease, choose one of options bellow");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void createAccount() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String firstName = "";
            String lastName = "";
            String email = "";

            do {
                System.out.println("Whats your first name?");
                firstName = scanner.nextLine();
            } while (firstName.isBlank());

            do {
                System.out.println("\nWhats your last name?");
                lastName = scanner.nextLine();
            } while (lastName.isBlank());

            do {
                System.out.println("\nWhats your email?");
                email = scanner.nextLine();
            } while (email.isBlank());

            // Save customer here


            System.out.println("\nName: " + firstName + " " + lastName + ", Email" +
                    ": " + email);

            System.out.println("\nWould you like to create other account? y/n");
            char answer = scanner.next().toLowerCase().charAt(0);

            if (answer == 'n') break;
        }
    }

    public static void findAndReserveRoom() {
        System.out.println("\nFind and Reserve Room");
    }

    public static void showMyReservations() {
        System.out.println("\nShow My Reservations");
    }
}
