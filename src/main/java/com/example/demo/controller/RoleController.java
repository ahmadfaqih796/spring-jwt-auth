package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private RoleRepository roleRepository;

   @PostMapping
   public RoleEntity create(@RequestBody RoleEntity roleService) {
      return roleRepository.save(roleService);
   }
}
