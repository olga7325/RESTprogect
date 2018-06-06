package com.RESTproject.RESTproject.controller;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String exception) {
        super(exception);
    }
}
