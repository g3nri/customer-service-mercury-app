package com.deviceshop.customer.tasks;

import com.deviceshop.customer.models.Customer;
import com.deviceshop.customer.storage.CustomerRepository;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@PreLoad(route = "v1.create.customer", instances = 10)
public class CreateCustomer implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String name = (String) input.get("name");
        String email = (String) input.get("email");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer = customerRepository.save(customer);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", customer.getId());
        result.put("name", customer.getName());
        result.put("email", customer.getEmail());
        return result;
    }
}
