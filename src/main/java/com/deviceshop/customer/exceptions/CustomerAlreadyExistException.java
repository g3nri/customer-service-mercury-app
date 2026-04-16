package com.deviceshop.customer.exceptions;

public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String email) {
        super("Customer with email " + email + " already exists");
    }
}
