package com.example.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.RoleEntity;

public class RoleSpecification {
   public static Specification<RoleEntity> containsKeyword(String keyword) {
      return (root, query, builder) -> {
         String pattern = "%" + keyword.toLowerCase() + "%";
         return builder.or(
               builder.like(builder.lower(root.get("name")), pattern),
               builder.like(builder.lower(root.get("description")), pattern),
               builder.like(builder.toString(root.get("id")), pattern));
      };
   }
}
