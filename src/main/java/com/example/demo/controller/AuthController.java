package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.repository.UsersRepository;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

   private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   @Autowired
   private UsersRepository usersRepository;

   @Autowired
   private AuthService authService;

   @PostMapping("/register")
   public UsersEntity register(@RequestBody UsersEntity users) {
      return usersRepository.save(users);
   }

   // @PostMapping("/login")
   // public ResponseEntity<String> login(@RequestBody UsersEntity users) {
   // boolean loginSuccessful = authService.login(users.getUsername(),
   // users.getPassword());
   // logger.info("username: {}", users.getUsername());
   // logger.info("password: {}", users.getPassword());
   // if (loginSuccessful) {
   // return ResponseEntity.ok("Login successful!");
   // } else {
   // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username
   // or password");
   // }
   // }

   @PostMapping("/login")
   public ResponseEntity<Map<String, Object>> login(@RequestBody UsersEntity users) {
      List<UsersEntity> existingUsers = authService.checkUser(users);
      logger.info("username: {}", users.getUsername());
      logger.info("password: {}", users.getPassword());
      if (!existingUsers.isEmpty()) {
         boolean loginSuccessful = authService.login(users.getUsername(), users.getPassword());
         if (loginSuccessful) {

            UsersEntity loggedInUser = existingUsers.get(0);
            logger.info("User found: {}", loggedInUser);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("status", HttpStatus.OK.value());
            responseData.put("message", "berhasil login");

            Map<String, Object> userData = new HashMap<>();
            userData.put("full_name", loggedInUser.getFull_name());
            userData.put("position", loggedInUser.getPosition());
            userData.put("telephone", loggedInUser.getTelephone());
            userData.put("username", loggedInUser.getUsername());
            userData.put("agent_id", loggedInUser.getAgent_id());
            userData.put("token", "i9ue8u44ef8ejf8e9f8ejrfh8he8rhe89erf"); // Ini hanya contoh, Anda bisa menghasilkan
                                                                           // token autentikasi di sini
            responseData.put("data", userData);
            return ResponseEntity.ok(responseData);
         } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            errorResponse.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
         }
      } else {
         Map<String, Object> errorResponse = new HashMap<>();
         errorResponse.put("status", HttpStatus.NOT_FOUND.value());
         errorResponse.put("message", "User not found");
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
   }
}
