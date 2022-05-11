package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;


public class ReimServlet extends HttpServlet{
	
	private ReimbursementService reimService = new ReimbursementService();
	private UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		
		System.out.println(uri);
		
		String[] urlSections = uri.split("/");
		
		if(urlSections.length == 3) {
			List<Reimbursement> list = reimService.getAllReimbursements();
			
			String json = objectMapper.writeValueAsString(list);
			
			PrintWriter print = resp.getWriter();
			print.print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		} else if(urlSections.length == 4) {
			String spacedName = urlSections[3].replace("%20", " ");
			Reimbursement reim = reimService.getReimbursementById(Integer.valueOf(spacedName));
			String json = objectMapper.writeValueAsString(reim);
			
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
		
		Reimbursement reim = objectMapper.readValue(body, Reimbursement.class);
		
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("username");
		reim.setAuthor(userService.getByUsername(username).get());
		
		if(!reim.equals(null)) {
			reimService.createReimbursement(reim);
			resp.setStatus(201);
		} else {
			resp.setStatus(406);
		}
	}
	
	@Override 
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder stBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line!=null) {
			stBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stBuilder);
		System.out.println(body);
		
		Reimbursement reim = objectMapper.readValue(body, Reimbursement.class);
		
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("username");
		User resolver = userService.getByUsername(username).get();
		
		Status status = reim.getStatus();
		
		Reimbursement dbReim = reimService.getReimbursementById(reim.getId());
		reim.setAuthor(dbReim.getAuthor());
		reim.setAmount(dbReim.getAmount());
		reim.setStatus(Status.PENDING);
		reim.setType(dbReim.getType());
	
		if(!reim.equals(null)) {
			reimService.process(reim, status, resolver);
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

}
