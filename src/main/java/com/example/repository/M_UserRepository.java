package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.M_User;

public interface M_UserRepository extends JpaRepository<M_User, String> {
	Optional<M_User> findByUserId(String userId);

	List<M_User> findAll();
}
