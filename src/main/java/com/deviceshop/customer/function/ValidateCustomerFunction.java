package com.deviceshop.customer.function;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "customer.validate", instances = 10)
public class ValidateCustomerFunction implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String name = input.get("name") == null ? null : input.get("name").toString();
        String email = input.get("email") == null ? null : input.get("email").toString();

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Field 'name' must not be blank");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Field 'email' must not be blank");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Field 'email' must be a valid email");
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}
