package com.deviceshop.customer.tasks;

import com.deviceshop.customer.models.Customer;
import com.deviceshop.customer.storage.CustomerRepository;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.exception.AppException;
import org.platformlambda.core.models.TypedLambdaFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "v1.get.customer", instances = 10)
public class GetCustomer implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) throws AppException {
        Object rawId = input.get("id");
        if (rawId == null) {
            throw new IllegalArgumentException("Path parameter 'id' is required");
        }

        Long id = Long.parseLong(rawId.toString());
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null) {
            throw new AppException(404, "Customer with id " + id + " not found");
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", customer.getId());
        result.put("name", customer.getName());
        result.put("email", customer.getEmail());
        return result;
    }
}
