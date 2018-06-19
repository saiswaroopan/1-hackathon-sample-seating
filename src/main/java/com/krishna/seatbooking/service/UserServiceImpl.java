package com.krishna.seatbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.repository.RoleRepository;
import com.krishna.seatbooking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private UserRepository userRepository;
	
    private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,
				UserRepository userRepository) {
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.roleRepository = roleRepository;
			this.userRepository = userRepository;
	}
	
	@Override
    public void save(User user) {
		logger.info("password in save before insertign to db-----  :"+user.getPassword());
		logger.info("country----  :"+user.getCountry());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findAll());
        userRepository.save(user);
    }
	
	@Override
    public User findByUserName(String userName) {
		logger.info("userName before retreving data from db :"+userName);
        //return userRepository.findByUserName(userName);
        final User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + userName);
		}
		return user;
		/*return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				user.getEnable(), true, true, true, getAuthorities(user.getRoles()));*/
    }
	
    


}
