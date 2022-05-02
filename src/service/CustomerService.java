package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService instance;
    private final Map<String, Customer> customers = new HashMap<String, Customer>();

    private CustomerService() {}

    public void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String email) {
        return customers.get(email);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }

        return instance;
    }
}
