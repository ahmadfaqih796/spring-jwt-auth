package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
   // Buat fitur search
   List<RoleEntity> findByNameContains(String name);
}
