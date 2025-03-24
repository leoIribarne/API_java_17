package com.iribarne.project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iribarne.project.domain.model.User;

public interface Userrepository extends JpaRepository<User, Long> {
}
