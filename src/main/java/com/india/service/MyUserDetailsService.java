package com.india.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.india.Repository.UserRepository;
import com.india.model.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        return new User("TCS", "TCS123", new ArrayList<>());
    	  Optional<com.india.model.User> user = userRepository.findByUserName(userName);
    	  System.out.println("user"+user);
          user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
          return user.map(MyUserDetails::new).get();
    }
}