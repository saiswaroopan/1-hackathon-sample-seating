package com.krishna.seatbooking.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	
    private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}    

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	try {
	    	logger.info("User Name :"+userName);
	        User user = userRepository.findByUserName(userName);
	        if (user == null) {
				throw new UsernameNotFoundException("No user found with username: " + userName);
			}
	        
	       // logger.info("user.getRoles() with porivded user name :"+user.getRoles());
	        /*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }*/
	        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
    	} catch(Exception e) {
    		throw new RuntimeException(e);
    	}

        
    }
    
    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		List<String> rolesList = (roles == null ? Collections.emptyList()
				: roles.stream().map(r -> r.getName()).collect(Collectors.toList()));
		return getGrantedAuthorities(rolesList);
	}
    
    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
   

}