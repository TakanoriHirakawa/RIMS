package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.M_SystemUser;


public interface SystemUserRepository extends JpaRepository<M_SystemUser, Integer>{
	Optional<M_SystemUser> findBySystemUserId(String systemUserId);
}
