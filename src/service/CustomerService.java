package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private Map<String, Customer> customers = new HashMap<String, Customer>();

    public CustomerService() {}

    public void addCustomer(String email, String firstName, String lastname) {
        customers.put(email, new Customer(firstName, lastname, email));
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomer() {
        return customers.values();
    }
}
