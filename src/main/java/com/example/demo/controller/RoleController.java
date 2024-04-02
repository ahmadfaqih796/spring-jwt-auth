package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.JwtResponseHandler;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.repository.RoleRepository;
import com.example.demo.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private JwtUtil jwtUtil;

   @Autowired
   private JwtResponseHandler jwtResponseHandler;

   @PostMapping
   public RoleEntity create(@RequestBody RoleEntity roleService) {
      return roleRepository.save(roleService);
   }

   // @GetMapping
   // public Iterable<RoleEntity> findAll() {
   // return roleRepository.findAll();
   // }

   @GetMapping
   public ResponseEntity<Map<String, Object>> findAll(@RequestHeader("Authorization") String token) {
      Iterable<RoleEntity> roles = roleRepository.findAll();
      return jwtResponseHandler.handleToken(token, roles);
   }

   // @GetMapping
   // public ResponseEntity<Map<String, Object>>
   // findAll(@RequestHeader("Authorization") String token) {
   // Iterable<RoleEntity> roles = roleRepository.findAll();
   // try {
   // String username = jwtUtil.getUsernameFromToken(token.replace("Bearer ", ""));
   // System.out.println("Valid Token: " + username);
   // Map<String, Object> responseData = new HashMap<>();
   // responseData.put("status", HttpStatus.OK.value());
   // responseData.put("message", "Berhasil dapatkan data role");
   // responseData.put("data", roles);
   // return ResponseEntity.ok(responseData);
   // } catch (ExpiredJwtException e) {
   // System.out.println("Invalid Token: " + e);
   // Map<String, Object> responseData = new HashMap<>();
   // responseData.put("status", HttpStatus.UNAUTHORIZED.value());
   // responseData.put("message", "punten session kowe habis, login enggeh");
   // responseData.put("data", null);
   // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
   // } catch (SignatureException e) {
   // System.out.println("Invalid Token: " + e);
   // Map<String, Object> responseData = new HashMap<>();
   // responseData.put("status", HttpStatus.UNAUTHORIZED.value());
   // responseData.put("message", "punten login awang");
   // responseData.put("data", null);
   // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
   // }
   // }

}
