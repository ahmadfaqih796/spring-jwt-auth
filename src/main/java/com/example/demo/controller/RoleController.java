package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.JwtResponseHandler;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.repository.RolePageRepository;
import com.example.demo.model.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private RolePageRepository rolePageRepository;

   @Autowired
   private JwtResponseHandler jwtResponseHandler;

   @PostMapping
   public RoleEntity create(@RequestBody RoleEntity roleService) {
      return roleRepository.save(roleService);
   }

   // Get data all tanpa pagination
   // @GetMapping
   // public ResponseEntity<Map<String, Object>>
   // findAll(@RequestHeader("Authorization") String token) {
   // Iterable<RoleEntity> roles = roleRepository.findAll();
   // return jwtResponseHandler.handleToken(token, roles);
   // }

   // Get data tanpa token authorization
   // @GetMapping
   // public Map<String, Object> findAll(
   // // @RequestHeader("Authorization") String token,
   // @RequestParam(defaultValue = "0") int page,
   // @RequestParam(defaultValue = "10") int size) {
   // Pageable pageable = PageRequest.of(page, size);
   // Page<RoleEntity> roles = rolePageRepository.findAll(pageable);
   // return buildResponseData(roles);
   // }

   @GetMapping
   public ResponseEntity<Map<String, Object>> findAll(
         @RequestHeader("Authorization") String token,
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size,
         @RequestParam(value = "sortField", defaultValue = "id") String sortField,
         @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
         @RequestParam(value = "keyword", defaultValue = "") String keyword) {
      Sort sort = Sort.by(sortField);
      if (sortDir.equals("asc")) {
         sort = sort.ascending();
      } else {
         sort = sort.descending();
      }
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<RoleEntity> roles = rolePageRepository.findAll(pageable);
      return jwtResponseHandler.handleToken(token, buildResponseData(roles));
   }

   private Map<String, Object> buildResponseData(Page<RoleEntity> roles) {
      Map<String, Object> response = new HashMap<>();
      response.put("content", roles.getContent());
      response.put("totalElements", roles.getTotalElements());
      response.put("totalPages", roles.getTotalPages());
      response.put("size", roles.getSize());
      response.put("number", roles.getNumber());
      return response;
   }

}
