package com.aydin.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class AuthorController {
    @GetMapping
    public String getData(){
        List<String> list =new ArrayList<>();
         return "";
    }

}
