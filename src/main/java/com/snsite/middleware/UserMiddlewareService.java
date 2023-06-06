package com.snsite.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.snsite.entity.UserEntity;
import com.snsite.repository.UserRepository;

@Service
public class UserMiddlewareService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUsername(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		return new CustomUserDetails(userEntity);
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		UserEntity user = userRepository.findById(id).get();
		if (user == null)
			throw new UsernameNotFoundException(id.toString());
		return new CustomUserDetails(user);
	}
}
