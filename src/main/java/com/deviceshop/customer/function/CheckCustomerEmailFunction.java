package com.deviceshop.customer.function;

import com.deviceshop.customer.storage.CustomerStore;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "customer.check.email", instances = 10)
public class CheckCustomerEmailFunction implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String email = input.get("email") == null ? null : input.get("email").toString();

        boolean exists = CustomerStore.existsByEmail(email);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("email", email);
        result.put("exists", exists);
        return result;
    }
}
