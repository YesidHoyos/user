package com.woloxgram.user.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.woloxgram.user.databuilder.UserTestDataBuilder;
import com.woloxgram.user.model.User;
import com.woloxgram.user.service.impl.UserService;
import com.woloxgram.user.util.exception.UserRestClientException;
import com.woloxgram.user.util.restclient.impl.UserRestClient;

@SpringBootTest
class UserServiceTest {
	
	@Mock
	private UserRestClient userRestClient;
	
	@InjectMocks
	private UserService userService;
	
	private static final String FIND_USERS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los usuarios";
	private static final String FIND_USER_ERROR = "Ocurrió un error al momento de consumir los datos para el usuario con id %s";

	@Test
	void getAllUsersSuccessfully() {
		//arrange
		List<User> usersExpected = new ArrayList<>();
		User leanne = new UserTestDataBuilder().build();
		usersExpected.add(leanne);
		when(userRestClient.getAllUsers()).thenReturn(usersExpected);
		
		//act
		List<User> users = userService.getAllUsers();
		
		//assert
		Assertions.assertTrue(users.contains(leanne));
	}
	
	@Test
	void getAllUsersWithError() {
		//arrange
		when(userRestClient.getAllUsers()).thenThrow(new UserRestClientException(FIND_USERS_ERROR));
		
		//act
		try {
			userService.getAllUsers();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_USERS_ERROR, e.getMessage());
		}		
	}
	
	@Test
	void getUserSuccessfully() {
		//arrange
		User leanne = new UserTestDataBuilder().withId(1L).build();
		when(userRestClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(leanne);
		
		//act
		User user = userService.getUserById(1L);
		
		//assert
		Assertions.assertEquals(leanne, user);
	}
	
	@Test
	void getUserWithError() {
		//arrange
		when(userRestClient.getUserById(ArgumentMatchers.anyLong())).thenThrow(new UserRestClientException(String.format(FIND_USER_ERROR,  1)));
		
		//act
		try {
			userService.getUserById(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_USER_ERROR,  1), e.getMessage());
		}
	}
}
