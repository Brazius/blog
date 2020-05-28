package com.codeacademy.tbrblogass.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogNotFoundExeption extends RuntimeException {
    public BlogNotFoundExeption(String message) {super(message);}
}
