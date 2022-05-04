package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet{
	
	private UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		
		System.out.println(uri);
		
		String[] urlSections = uri.split("/");
		
		if(urlSections.length == 3) {
			List<User> list = userService.getUserList();
			
			String json = objectMapper.writeValueAsString(list);
			
			PrintWriter print = resp.getWriter();
			print.print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		} else if(urlSections.length == 4) {
			String spacedName = urlSections[3].replace("%20", " ");
			User user = userService.getByUsername(spacedName).get();
			String json = objectMapper.writeValueAsString(user);
			
			PrintWriter print = resp.getWriter();
			print.print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		BufferedReader reader = req.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine(); 
		
		while(line != null) {
			stringBuilder.append(line);
			line = reader.readLine(); 
		}
		
		String body = new String(stringBuilder);
		
		User user = objectMapper.readValue(body, User.class);
		
		if(!user.equals(null)) {
			userService.createUser(user);
			resp.setStatus(201);
		} else {
			resp.setStatus(406);
		}
	}

}
