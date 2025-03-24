package com.iribarne.project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iribarne.project.domain.model.CartSection;

@Repository
public interface CartSectionRepository extends JpaRepository<CartSection, Long> {
} 