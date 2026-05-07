package com.deviceshop.customer.tasks;

import com.deviceshop.customer.dto.CustomerRequest;
import com.deviceshop.customer.dto.CustomerResponse;
import com.deviceshop.customer.models.Customer;
import com.deviceshop.customer.storage.CustomerRepository;
import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@PreLoad(route = "v1.create.customer", instances = 10)
public class CreateCustomer implements TypedLambdaFunction<CustomerRequest, CustomerResponse> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse handleEvent(Map<String, String> headers, CustomerRequest input, int instance) {
        Customer customer = toEntity(input);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.from(savedCustomer);
    }

    private static Customer toEntity(CustomerRequest input) {
        Customer customer = new Customer();
        customer.setName(input.name);
        customer.setEmail(input.email);
        return customer;
    }
}
