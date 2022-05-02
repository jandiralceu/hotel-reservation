package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Please, provide a valid email");
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
    public String toString() {
        return "Name: " + fullName() + "\t" + "E-mail: " + getEmail();
    }
}
