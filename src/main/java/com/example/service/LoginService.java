package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.M_SystemUser;
import com.example.repository.SystemUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final SystemUserRepository repository;
	
	public Optional<M_SystemUser> searchUserById(Integer id){
		return repository.findById(id);
	}
	
	public Optional<M_SystemUser> searchUserBySystemUserId(String systemUserId){
		return repository.findBySystemUserId(systemUserId);
	};
}
