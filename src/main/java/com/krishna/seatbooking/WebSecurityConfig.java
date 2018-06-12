package com.krishna.seatbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticatonManagerBean() throws Exception {
		 return super.authenticationManagerBean();
	 }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("***** In configure method ****** ");
		/*http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().authenticated().and().httpBasic()
				.and().csrf().disable();*/
		
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/registration").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
			
		
	}

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}Krk@number8").roles("USER");
	}*/
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("*****configureGlobal method ****** ");
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}