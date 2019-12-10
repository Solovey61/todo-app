package net.sytes.solovey.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class APIResponse {
    public static ResponseEntity<Object> getAPIOkResponse(Object response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getAPINotFoundResponse(Object response) {
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

