package com.woloxgram.user.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.user.model.User;
import com.woloxgram.user.service.databuilder.UserTestDataBuilder;
import com.woloxgram.user.util.restclient.impl.UserRestClient;

@SpringBootTest
class UserRestClientTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private UserRestClient userRestClient;
	
	private static final String FIND_USERS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los usuarios";
	
	@Test
	void getAllUsersSuccessfully() {
		//arrange
		List<User> usersExpected = new ArrayList<>();
		User leanne = new UserTestDataBuilder().build();
		usersExpected.add(leanne);
		ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(usersExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any()))
		.thenReturn(response);
		
		//act
		List<User> users = userRestClient.getAllUsers();
		
		//assert
		Assertions.assertTrue(users.contains(leanne));
	}
	
	@Test
	void getAllUsersWithEstatus404() {
		//arrange
		ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any()))
		.thenReturn(response);
		
		//act
		try {
			userRestClient.getAllUsers();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_USERS_ERROR, e.getMessage());
		}		
	}
}