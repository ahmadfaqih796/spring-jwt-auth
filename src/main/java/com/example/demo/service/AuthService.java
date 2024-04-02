package com.example.demo.service;

import java.util.List;

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
      // String encodedPassword = MD5PasswordEncoder.encode(users.getPassword());
      // users.setPassword(encodedPassword);
      return usersRepository.save(users);
   }

   public List<UsersEntity> checkUser(UsersEntity users) {
      return usersRepository.findByUsername(users.getUsername());
   }

   public boolean login(String username, String password) {
      // String encodedPassword = MD5PasswordEncoder.encode(password);
      return usersRepository.findByUsernameAndPassword(username, password).isPresent();
   }

}
