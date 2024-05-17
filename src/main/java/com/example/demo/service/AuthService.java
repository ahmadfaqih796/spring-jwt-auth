package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.repository.AuthRepository;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class AuthService {

   @Autowired
   private AuthRepository authRepository;

   // Register User
   public UsersEntity register(UsersEntity users) {
      // String encodedPassword = MD5PasswordEncoder.encode(users.getPassword());
      // users.setPassword(encodedPassword);
      return authRepository.save(users);
   }

   public List<UsersEntity> checkUser(UsersEntity users) {
      return authRepository.findByUsername(users.getUsername());
   }

   public boolean login(String username, String password) {
      // String encodedPassword = MD5PasswordEncoder.encode(password);
      return authRepository.findByUsernameAndPassword(username, password).isPresent();
   }

}
