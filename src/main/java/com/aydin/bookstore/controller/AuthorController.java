package com.aydin.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/bookstore/v1/author")
@RequestMapping
public class AuthorController {
    @GetMapping
    public String getData() {
        return "Salam Java";
    }

}
