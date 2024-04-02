package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.repository.UsersRepository;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class AuthService {

   @Autowired
   private UsersRepository usersRepository;

   // Register User
   public UsersEntity register(UsersEntity users) {
      return usersRepository.save(users);
   }

}
