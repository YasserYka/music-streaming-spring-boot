package io.musicStreaming.start.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.musicStreaming.start.configuration.UserDetail;
import io.musicStreaming.start.model.User;
import io.musicStreaming.start.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  repository.findById(username);
		user.orElseThrow(()->new UsernameNotFoundException("Coulding find " + username));
		return user.map(UserDetail::new).get();
	}

}
