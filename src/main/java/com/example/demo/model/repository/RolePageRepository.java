package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.RoleEntity;

// public interface RolePageRepository extends PagingAndSortingRepository<RoleEntity, Long> {
//    Page<RoleEntity> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
//    // public Page<DepartmentResponse> findDataSearching(String keyword, Pageable
//    // page);
// }

public interface RolePageRepository extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {
}
