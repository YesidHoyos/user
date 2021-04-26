package com.woloxgram.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woloxgram.user.model.User;
import com.woloxgram.user.service.IUserService;
import com.woloxgram.user.util.restclient.IUserRestClient;

@Service
public class UserService implements IUserService {
	
	private IUserRestClient userRestClient;

	public UserService(IUserRestClient userRestClient) {
		this.userRestClient = userRestClient;
	}

	@Override
	public List<User> getAllUsers() {
		return userRestClient.getAllUsers();
	}

	@Override
	public User getUserById(Long userId) {
		return userRestClient.getUserById(userId);
	}

}
