package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.JwtResponseHandler;
import com.example.demo.model.entity.UsersEntity;
import com.example.demo.model.repository.UsersRepository;
import com.example.demo.specification.UserSpecification;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UsersRepository usersRepository;

   @Autowired
   private JwtResponseHandler jwtResponseHandler;

   @GetMapping
   public ResponseEntity<Map<String, Object>> findAll(
         @RequestHeader("Authorization") String token,
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "10") int size,
         @RequestParam(value = "sortField", defaultValue = "position") String sortField,
         @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
         @RequestParam(value = "keyword", defaultValue = "") String keyword) {
      Sort sort = Sort.by(sortField);
      if (sortDir.equals("asc")) {
         sort = sort.ascending();
      } else {
         sort = sort.descending();
      }
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<UsersEntity> roles;
      if (keyword.isEmpty()) {
         roles = usersRepository.findAll(pageable);
      } else {
         Specification<UsersEntity> spec = UserSpecification.containsKeyword(keyword);
         roles = usersRepository.findAll(spec, pageable);
      }
      return jwtResponseHandler.handleToken(token, buildResponseData(roles));
   }

   private Map<String, Object> buildResponseData(Page<UsersEntity> roles) {
      Map<String, Object> response = new HashMap<>();
      response.put("content", roles.getContent());
      response.put("totalElements", roles.getTotalElements());
      response.put("totalPages", roles.getTotalPages());
      response.put("size", roles.getSize());
      response.put("number", roles.getNumber());
      return response;
   }
}
