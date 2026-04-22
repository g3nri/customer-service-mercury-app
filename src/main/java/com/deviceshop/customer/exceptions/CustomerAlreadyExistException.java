package com.deviceshop.customer.exceptions;

import org.platformlambda.core.exception.AppException;

public class CustomerAlreadyExistException extends AppException {

    public CustomerAlreadyExistException(String email) {
        super(409, "Customer with email " + email + " already exists");
    }
}
