package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

}
