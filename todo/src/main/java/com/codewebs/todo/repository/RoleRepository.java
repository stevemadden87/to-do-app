package com.codewebs.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewebs.todo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

}
