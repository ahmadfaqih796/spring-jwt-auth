package com.example.demo.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.entity.RoleEntity;

public interface RolePageRepository extends PagingAndSortingRepository<RoleEntity, Long> {

}
