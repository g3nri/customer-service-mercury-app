package com.deviceshop.customer.tasks;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "v1.customer.response", instances = 10)
public class BuildCustomerResponse implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", input.get("id"));
        result.put("name", input.get("name"));
        result.put("email", input.get("email"));
        return result;
    }
}
