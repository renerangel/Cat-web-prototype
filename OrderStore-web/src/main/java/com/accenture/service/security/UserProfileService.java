package com.accenture.service.security;

import java.util.List;

import com.accenture.model.security.UserProfile;



public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
