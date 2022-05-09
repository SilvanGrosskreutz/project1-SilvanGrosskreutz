package com.revature.controllers;

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
import com.revature.services.ReimbursementService;

public class FinanceManagerServlet extends HttpServlet{
	
//	private ReimbursementService reimService = new ReimbursementService();
//	private ObjectMapper objectMapper = new ObjectMapper();
	
	// TODO: Change to doPut()
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		//Check that the user is logged in
		
		HttpSession session = req.getSession(false);
		
		PrintWriter printWriter = resp.getWriter();
		
		if(session!=null) {
			String username = (String)session.getAttribute("username");
			printWriter.print("<h2>Welcome "+username+", you successfully logged in.</h2>");
			printWriter.print("<a href='logout'>Click here to log out</a>");
		} else {
			printWriter.print("<h1>YOU ARE NOT LOGGED IN!!!!!!</h1>");
		}
	}

}
