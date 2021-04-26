package com.woloxgram.user.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.woloxgram.user.databuilder.UserTestDataBuilder;
import com.woloxgram.user.model.User;
import com.woloxgram.user.service.impl.UserService;

@AutoConfigureMockMvc
@WebMvcTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	void getAllUsersTest() throws Exception {
		//arrange
		List<User> users = new ArrayList<>();
		User leanne = new UserTestDataBuilder().build();
		users.add(leanne);
		String bodyExpected = "[{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}]";
		when(userService.getAllUsers()).thenReturn(users);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getUserByIdTest() throws Exception {
		//arrange
		User leanne = new UserTestDataBuilder().build();
		String bodyExpected = "{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}";
		when(userService.getUserById(ArgumentMatchers.anyLong())).thenReturn(leanne);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
}
