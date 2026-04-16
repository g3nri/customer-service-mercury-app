package com.deviceshop.customer.tasks;

import org.platformlambda.core.annotations.PreLoad;
import org.platformlambda.core.models.TypedLambdaFunction;
import org.platformlambda.core.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@PreLoad(route = "v1.error.handler", instances = 10)
public class ErrorHandler implements TypedLambdaFunction<Map<String, Object>, Map<String, Object>> {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    private static final String TYPE = "type";
    private static final String ERROR = "error";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String STACK = "stack";

    @Override
    public Map<String, Object> handleEvent(Map<String, String> headers, Map<String, Object> input, int instance) {
        if (input.containsKey(STACK)) {
            var map = Utility.getInstance().stackTraceToMap(String.valueOf(input.get(STACK)));
            log.info("{}", map);
        }
        if (input.containsKey(STATUS) && input.containsKey(MESSAGE)) {
            log.info("Error handler - status={} message={}", input.get(STATUS), input.get(MESSAGE));
            Map<String, Object> error = new HashMap<>();
            error.put(TYPE, ERROR);
            error.put(STATUS, input.get(STATUS));
            error.put(MESSAGE, input.get(MESSAGE));
            return error;
        } else {
            return Collections.emptyMap();
        }
    }
}
