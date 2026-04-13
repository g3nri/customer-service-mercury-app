package com.deviceshop.customer.function;

import com.deviceshop.customer.model.Customer;
import com.deviceshop.customer.storage.CustomerStore;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "customer.create", instances = 10)
public class CreateCustomerFunction implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String name = input.get("name") == null ? null : input.get("name").toString();
        String email = input.get("email") == null ? null : input.get("email").toString();

        Customer customer = CustomerStore.save(name, email);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", customer.getId());
        result.put("name", customer.getName());
        result.put("email", customer.getEmail());
        return result;
    }
}
