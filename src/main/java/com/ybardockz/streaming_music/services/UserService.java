package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
	
	public List<User> findAll() {
		Sort sort = Sort.by("username").descending();
		return repository.findAll(sort);
	}
	
	public void insert(User user) {
		repository.save(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void update(User user, Long id) {
		try {
			User userExisting = findById(id);
			userExisting.setUsername(user.getUsername());
			userExisting.setEmail(user.getEmail());
			userExisting.setPassword(user.getPassword());
			
			repository.save(userExisting);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
