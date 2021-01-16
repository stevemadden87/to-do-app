package com.codewebs.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewebs.todo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	User findByUsername(String username);
	User findById(Integer id);
}
