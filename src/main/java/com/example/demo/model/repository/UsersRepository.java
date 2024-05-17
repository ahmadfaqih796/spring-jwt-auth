package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.UsersEntity;

// public interface UsersRepository extends CrudRepository<UsersEntity, Long> {

//    List<UsersEntity> findByUsername(String username);

//    Optional<UsersEntity> findByUsernameAndPassword(String username, String password);
// }

public interface UsersRepository extends JpaRepository<UsersEntity, Long>, JpaSpecificationExecutor<UsersEntity> {
}
