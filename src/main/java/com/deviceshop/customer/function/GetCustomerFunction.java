package com.deviceshop.customer.function;

import com.deviceshop.customer.model.Customer;
import com.deviceshop.customer.storage.CustomerStore;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "customer.get", instances = 10)
public class GetCustomerFunction implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        Object rawId = input.get("id");
        if (rawId == null) {
            throw new IllegalArgumentException("Path parameter 'id' is required");
        }

        Long id = Long.parseLong(rawId.toString());
        Customer customer = CustomerStore.findById(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + id + " not found");
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", customer.getId());
        result.put("name", customer.getName());
        result.put("email", customer.getEmail());
        return result;
    }
}
