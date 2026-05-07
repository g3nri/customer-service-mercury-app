package com.deviceshop.customer.tasks;

import com.deviceshop.customer.dto.CustomerRequest;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.Map;
import java.util.regex.Pattern;

@PreLoad(route = "v1.validate.customer", instances = 10)
public class ValidateCustomer implements TypedLambdaFunction<CustomerRequest, CustomerRequest> {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_EMAIL_LENGTH = 255;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final String FIELD_NAME = "name";
    private static final String FIELD_EMAIL = "email";

    @Override
    public CustomerRequest handleEvent(Map<String, String> headers, CustomerRequest input, int instance) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }

        String name = trimAndValidate(input.name, FIELD_NAME, MAX_NAME_LENGTH);
        String email = trimAndValidate(input.email, FIELD_EMAIL, MAX_EMAIL_LENGTH);

        validateEmailFormat(email);

        return new CustomerRequest(name,  email);
    }

    // Checks if the value is null or blank, trims it, and validates the length.
    // It throws IllegalArgumentException if any validation fails.
    private static String trimAndValidate(String value, String field, int maxLength) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Field '" + field + "' must not be blank");
        }

        String trimmed = value.trim();
        if (trimmed.length() > maxLength) {
            throw new IllegalArgumentException(
                    "Field '" + field + "' must not exceed " + maxLength + " characters"
            );
        }
        return trimmed;
    }

    private static void validateEmailFormat(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Field 'email' must be a valid email");
        }
    }
}
