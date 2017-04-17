package com.accenture.repository;

import java.util.List;

import com.accenture.model.security.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
