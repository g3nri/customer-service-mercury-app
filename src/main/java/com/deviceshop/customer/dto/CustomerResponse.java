package com.deviceshop.customer.dto;

import com.deviceshop.customer.models.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    public Long id;
    public String name;
    public String email;

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
