package com.javasampleapproach.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.h2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
