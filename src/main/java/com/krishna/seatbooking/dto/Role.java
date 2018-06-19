package com.krishna.seatbooking.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name="roles")
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String name;
    
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	


}
