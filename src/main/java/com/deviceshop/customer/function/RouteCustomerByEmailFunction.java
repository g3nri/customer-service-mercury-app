package com.deviceshop.customer.function;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.Map;

@PreLoad(route = "customer.route.by.email", instances = 10)
public class RouteCustomerByEmailFunction implements TypedLambdaFunction<Map<String, Object>, Boolean> {

    @Override
    public Boolean handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        Object rawExists = input.get("exists");
        return rawExists instanceof Boolean && (Boolean) rawExists;
    }
}
