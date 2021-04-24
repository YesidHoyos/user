package com.woloxgram.user.util.restclient.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.user.model.User;
import com.woloxgram.user.util.exception.UserRestClientException;
import com.woloxgram.user.util.restclient.IUserRestClient;

@Component
public class UserRestClient implements IUserRestClient {

	private static final String FIND_ALL_URL = "https://jsonplaceholder.typicode.com/users";
	private static final String FIND_USERS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los usuarios";
	private static final String USER_BY_ID_URL = "https://jsonplaceholder.typicode.com/users/%s";
	private static final String FIND_USER_ERROR = "Ocurrió un error al momento de consumir los datos para el usuario con id %s";
	
	private RestTemplate restTemplate;

	public UserRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<User> getAllUsers() {
		ResponseEntity<List<User>> response = null;
		try {
			response = restTemplate.
					exchange(FIND_ALL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){});
		} catch (RestClientException e) {
			throw new UserRestClientException(FIND_USERS_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new UserRestClientException(FIND_USERS_ERROR);
		}
		
		return response.getBody();
	}
	
	@Override
	public User getUserById(Long userId) {
		User user = new User();
		try {
			user = restTemplate.getForObject(String.format(USER_BY_ID_URL, userId), User.class);
		} catch (RestClientException e) {
			throw new UserRestClientException(String.format(FIND_USER_ERROR, userId), e);
		}
		return user;
	}
}
