package com.example.demo.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

   List<UsersEntity> findByUsername(String username);

   Optional<UsersEntity> findByUsernameAndPassword(String username, String password);
}
