package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.M_User;
import com.example.repository.M_UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final M_UserRepository repository;
	
	public Optional<M_User> searchUserByUserId(String userId){
		return repository.findByUserId(userId);
	}
}
