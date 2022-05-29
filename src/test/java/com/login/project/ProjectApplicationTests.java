package com.login.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.project.controller.UserController;
import com.login.project.model.LoginDTO;
import com.login.project.model.User;
import com.login.project.repository.UserRepository;

@WebMvcTest(UserController.class)
class ProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private static List<User> users = new ArrayList<User>() {{
		add(new User("user1", "thisishobas1234", "user1@gmail.com"));
	}};
	
	@Test
	public void loginFailedDueToWrongUsernameEmailAndPassword() throws Exception {
		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/login")
				.content(asJsonString(new LoginDTO("userNotExist", "mockPassword")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid username/email or password"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("401"));
	}
	
	@Test
	public void loginSuccessWithUsername() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/login")
				.content(asJsonString(new LoginDTO("user1", "thisishobas1234")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
	}
	
	@Test
	public void loginSuccessWithEmail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/login")
				.content(asJsonString(new LoginDTO("user1@gmail.com", "thisishobas1234")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
	}
}
