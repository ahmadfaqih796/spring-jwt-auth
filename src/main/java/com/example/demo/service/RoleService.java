package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.repository.RoleRepository;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class RoleService {

   @Autowired
   private RoleRepository roleRepository;

   public RoleEntity create(RoleEntity role) {
      return roleRepository.save(role);
   }

   public Iterable<RoleEntity> findAll() {
      return roleRepository.findAll();
   }

}
