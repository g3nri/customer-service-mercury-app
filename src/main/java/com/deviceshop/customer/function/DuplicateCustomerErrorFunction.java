package com.deviceshop.customer.function;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "customer.duplicate.error", instances = 10)
public class DuplicateCustomerErrorFunction implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String email = input.get("email") == null ? null : input.get("email").toString();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("type", "error");
        result.put("status", 400);
        result.put("message", "Customer with email " + email + " already exists");
        return result;
    }
}
