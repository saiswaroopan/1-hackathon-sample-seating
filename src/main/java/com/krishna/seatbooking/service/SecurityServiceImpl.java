package com.krishna.seatbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private UserDetailsService userDetailsService;
	
	private AuthenticationManager authenticationManager;
	 
	 public SecurityServiceImpl(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
			this.userDetailsService = userDetailsService;
	 }
	 
	@Override
	public void autologin(String userName, String password) {
		
		logger.info(" ----- userName in autologin--- :"+userName);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		logger.info(" ----- In Auto login method get password from db---"+userDetails.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        logger.info(" ----- username and password authentication--"+usernamePasswordAuthenticationToken.isAuthenticated());
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info(" ----- Auto login successful authentication for user--"+userName);
            logger.debug(String.format("Auto login %s successfully!", userName));
        }
		
	}
    
	public static String findLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}
	
	


}
