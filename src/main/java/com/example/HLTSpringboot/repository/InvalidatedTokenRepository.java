package com.example.HLTSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HLTSpringboot.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
