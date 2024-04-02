package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.repository.RoleRepository;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private JwtUtil jwtUtil;

   @PostMapping
   public RoleEntity create(@RequestBody RoleEntity roleService) {
      return roleRepository.save(roleService);
   }

   // @GetMapping
   // public Iterable<RoleEntity> findAll() {
   // return roleRepository.findAll();
   // }

   @GetMapping
   public ResponseEntity<?> findAll(@RequestHeader("Authorization") String token) {
      // Mengambil token dari header Authorization
      String username = jwtUtil.getUsernameFromToken(token.replace("Bearer ", ""));
      System.out.println("Valid Token: " + username);
      if (username != null) {
         Iterable<RoleEntity> roles = roleRepository.findAll();
         return ResponseEntity.ok(roles);
      } else {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
      }
   }

}
