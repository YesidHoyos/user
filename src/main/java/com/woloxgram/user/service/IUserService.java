package com.woloxgram.user.service;

import java.util.List;

import com.woloxgram.user.model.User;

public interface IUserService {

	public List<User> getAllUsers();
	public User getUserById(Long userId);
}
