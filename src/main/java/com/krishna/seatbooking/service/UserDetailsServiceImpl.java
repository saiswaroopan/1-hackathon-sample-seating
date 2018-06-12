package com.krishna.seatbooking.service;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krishna.seatbooking.dto.Role;
import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UserRepository userRepository;

	    

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	logger.info("User Name :"+userName);
        User user = userRepository.findByUserName(userName);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
   

}