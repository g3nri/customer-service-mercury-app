package com.deviceshop.customer.tasks;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@PreLoad(route = "v1.validate.customer", instances = 10)
public class ValidateCustomer implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_EMAIL_LENGTH = 255;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String name = input.get("name") == null ? null : input.get("name").toString().trim();
        String email = input.get("email") == null ? null : input.get("email").toString().trim();

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Field 'name' must not be blank");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Field 'name' must not exceed " + MAX_NAME_LENGTH + " characters");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Field 'email' must not be blank");
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            throw new IllegalArgumentException("Field 'email' must not exceed " + MAX_EMAIL_LENGTH + " characters");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Field 'email' must be a valid email");
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}
