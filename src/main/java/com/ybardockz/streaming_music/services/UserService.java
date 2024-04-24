package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.UserRepository;
import com.ybardockz.streaming_music.services.exceptions.DbException;
import com.ybardockz.streaming_music.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<User> findAll() {
		Sort sort = Sort.by("username").descending();
		return repository.findAll(sort);
	}
	
	public void insert(User user) {
		repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	public void update(User user, Long id) {
		try {
			User userExisting = findById(id);
			userExisting.setUsername(user.getUsername());
			userExisting.setEmail(user.getEmail());
			userExisting.setPassword(user.getPassword());
			
			repository.save(userExisting);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}

}
