package com.example.HLTSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HLTSpringboot.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
