package com.deviceshop.customer.storage;

import com.deviceshop.customer.model.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerStore {

    private static final Map<Long, Customer> STORAGE = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private CustomerStore() {
    }

    public static Customer save(String name, String email) {
        validateUniqueEmail(email);

        Long id = ID_GENERATOR.getAndIncrement();
        Customer customer = new Customer(id, name, email);
        STORAGE.put(id, customer);
        return customer;
    }

    public static Customer findById(Long id) {
        return STORAGE.get(id);
    }

    private static void validateUniqueEmail(String email) {
        boolean exists = STORAGE.values().stream()
                .anyMatch(customer -> customer.getEmail() != null
                        && customer.getEmail().equalsIgnoreCase(email));

        if (exists) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }
    }
}
