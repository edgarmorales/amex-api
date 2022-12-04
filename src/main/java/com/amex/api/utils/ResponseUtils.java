package com.amex.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object response) {
        return new ResponseEntity<>(formattedResponse(message, status, response), status);
    }

    public static Object formattedResponse(String message, HttpStatus status, Object response) {
        Map<String, Object> formattedResponse = new HashMap();
        formattedResponse.put("message", message);
        formattedResponse.put("status", status.value());
        formattedResponse.put("data", response);
        return formattedResponse;
    }
}