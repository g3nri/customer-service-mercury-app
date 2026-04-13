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
        Long id = ID_GENERATOR.getAndIncrement();
        Customer customer = new Customer(id, name, email);
        STORAGE.put(id, customer);
        return customer;
    }

    public static Customer findById(Long id) {
        return STORAGE.get(id);
    }

    public static boolean existsByEmail(String email) {
        return STORAGE.values().stream()
                .anyMatch(customer -> customer.getEmail() != null
                        && customer.getEmail().equalsIgnoreCase(email));
    }
}
