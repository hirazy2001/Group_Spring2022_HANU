package com.example.group_hanu_spring2022.repository;

import com.example.group_hanu_spring2022.model.Role;
import com.example.group_hanu_spring2022.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
