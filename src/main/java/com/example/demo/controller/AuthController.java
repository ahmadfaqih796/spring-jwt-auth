package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.repository.UsersRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

   @Autowired
   private UsersRepository usersRepository;

   @PostMapping("/register")
   public UsersEntity register(@RequestBody UsersEntity users) {
      return usersRepository.save(users);
   }
}
