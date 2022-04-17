package model;

public class Driver {
    public static void main(String[] args) {
        // Success
        Customer customer = new Customer("Jandir", "Alceu", "me@jandir.co");

        System.out.println(customer);

        // Error
        Customer customer2 = new Customer("Jandir", "Alceu", "invalidemail");

        System.out.println(customer2);
    }
}
