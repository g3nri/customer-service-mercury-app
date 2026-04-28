package com.deviceshop.customer.dto;

import org.platformlambda.core.serializers.SimpleMapper;

import java.util.Map;

public class CustomerRequest {

    public String name;
    public String email;

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap() {
        return SimpleMapper.getInstance().getMapper().readValue(this, Map.class);
    }
}
