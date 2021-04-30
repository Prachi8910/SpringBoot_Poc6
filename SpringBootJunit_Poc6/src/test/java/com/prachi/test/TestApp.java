package com.prachi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prachi.controller.UserController;
import com.prachi.model.User;
import com.prachi.repository.UserRepository;
import com.prachi.service.UserService;


@WebMvcTest(UserController.class)
public class TestApp {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository repo;
	
	@MockBean
	private UserService service;
	
	ObjectMapper om = new ObjectMapper();
	
	public static List<User> setUp() {
		User user = new User();
		user.setId(1L);
		user.setFirstName("John");
		user.setLastName("Lennon");
		user.setPhoneNumber("8909990006");
		user.setEmail("jl@gmail.com");
		user.setCity("pnvl");
		user.setCountry("India");
		user.setState("MHA");
		user.setZipCode("456213");
		
		User user1 = new User();
		user1.setId(2L);
		user1.setFirstName("Mathew");
		user1.setLastName("Richardson");
		user1.setPhoneNumber("8909990093");
		user1.setEmail("mr@gmail.com");
		user1.setCity("Dadar");
		user1.setCountry("India");
		user1.setState("MHA");
		user1.setZipCode("452890");
		
		List<User> listUser = new ArrayList<User>();
		listUser.add(user);
		listUser.add(user1);
		
		return listUser;
	}
	
	@Test
	public void addUserTest() throws Exception {
		Mockito.when(service.addUser(TestApp.setUp().get(0))).thenReturn(1L);
		String payload = om.writeValueAsString(TestApp.setUp().get(0));
		MvcResult result = mockMvc
				.perform(post("/users").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
		
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		List<User> lst = TestApp.setUp();
		String response = om.writeValueAsString(lst);
		Mockito.when(service.getUsers()).thenReturn(lst);
		MvcResult result = mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String userRes = result.getResponse().getContentAsString();
		assertEquals(response, userRes);
				
	}
	
	@Test
	public void updateUserTest() throws Exception {
		Mockito.when(service.updateUser(TestApp.setUp().get(1).getId(),TestApp.setUp().get(1))).thenReturn(TestApp.setUp().get(1));
		String payload = om.writeValueAsString(TestApp.setUp().get(1));
		MvcResult result = mockMvc
				.perform(put("/users/update/1").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		
		MvcResult result = mockMvc.perform(delete("/users/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response = result.getResponse().getStatus();
		assertEquals(200, response);
		assertEquals("User deleted with id:1", result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void getUserbyIdTest() throws Exception {
		String response=om.writeValueAsString(TestApp.setUp().get(0));
		Mockito.when(service.getUserById(1L)).thenReturn(java.util.Optional.of(TestApp.setUp().get(0)));
		MvcResult result = mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response1 = result.getResponse().getStatus();
		assertEquals(200, response1);
		
	}
	
	
	
	@Test
	public void searchByNameTest() throws Exception {
		User user21 = new User();
		user21.setId(4L);
		user21.setFirstName("Shreya2");
		user21.setLastName("wrt");
		user21.setPhoneNumber("8877112200");
		user21.setEmail("sh@gmail.com");
		user21.setCity("Pune");
		user21.setCountry("India");
		user21.setState("M.H");
		user21.setZipCode("411108");
		List<User> list = new ArrayList<User>();
		list.add(user21);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByFirstName("Shreya2")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/users/getFname/Shreya2").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	@Test
	public void searchByZipCodeTest() throws Exception {
		User user2 = new User();
		user2.setId(5L);
		user2.setFirstName("Snehal");
		user2.setLastName("Kedar");
		user2.setPhoneNumber("8877190823");
		user2.setEmail("sk@gmail.com");
		user2.setCity("Dadar");
		user2.setCountry("India");
		user2.setState("M.H");
		user2.setZipCode("411093");
		
		User user3 = new User();
		user3.setId(6L);
		user3.setFirstName("Neha");
		user3.setLastName("Kumbhar");
		user3.setPhoneNumber("8877134567");
		user3.setEmail("nk@gmail.com");
		user3.setCity("Thane");
		user3.setCountry("India");
		user3.setState("M.H");
		user3.setZipCode("409456");
		List<User> list = new ArrayList<User>();
		list.add(user2);
		list.add(user3);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByZipCode("409456")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/users/getZipcode/409456").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	

}
