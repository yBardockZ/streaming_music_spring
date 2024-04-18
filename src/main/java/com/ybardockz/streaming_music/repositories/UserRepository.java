package com.ybardockz.streaming_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ybardockz.streaming_music.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
