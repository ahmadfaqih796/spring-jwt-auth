package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private JwtResponseHandler jwtResponseHandler;

   @PostMapping
   public RoleEntity create(@RequestBody RoleEntity roleService) {
      return roleRepository.save(roleService);
   }

   @GetMapping
   public ResponseEntity<Map<String, Object>> findAll(@RequestHeader("Authorization") String token) {
      Iterable<RoleEntity> roles = roleRepository.findAll();
      return jwtResponseHandler.handleToken(token, roles);
   }

}
