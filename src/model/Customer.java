package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error on creating " +
                    "account. Please, provide a valid " +
                    "email. \nExpected format: someone@example.com");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) obj;

        return this.email.equals(customer.email) &&
                this.firstName.equals(customer.firstName) &&
                this.lastName.equals(customer.lastName);
    }

    @Override
    public String toString() {
        return "Name: " + fullName() + "\t" + "E-mail: " + getEmail();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
