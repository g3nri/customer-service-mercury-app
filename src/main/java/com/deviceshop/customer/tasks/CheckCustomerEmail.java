package com.deviceshop.customer.tasks;

import com.deviceshop.customer.exceptions.CustomerAlreadyExistException;
import com.deviceshop.customer.storage.CustomerRepository;
import org.platformlambda.core.annotations.KernelThreadRunner;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@PreLoad(route = "v1.check.customer.email", instances = 10)
@KernelThreadRunner
public class CheckCustomerEmail implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        String email = input.get("email") == null ? null : input.get("email").toString();

        if (customerRepository.existsByEmailIgnoreCase(email)) {
            throw new CustomerAlreadyExistException(email);
        }

        return input;
    }
}
