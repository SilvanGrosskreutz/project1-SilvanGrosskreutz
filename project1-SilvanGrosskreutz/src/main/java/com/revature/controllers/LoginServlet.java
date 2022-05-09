package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class LoginServlet extends HttpServlet{
	
	protected UserDAO userDAO = new UserDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String username = req.getParameter("userId");
		String password = req.getParameter("password");
		
		User user = userDAO.getByUsername(username).get();
		
		RequestDispatcher reqDispatcher = null;
		PrintWriter printWriter = resp.getWriter();
		
		if(username.equals(user.getUsername()) && password.equals(user.getPassword()) 
				&& user.getRole().equals(Role.EMPLOYEE)) {
			// create a session
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Send them to the Reimbursement page
			reqDispatcher = req.getRequestDispatcher("reim.html");
			reqDispatcher.forward(req, resp);
			
		} else if(username.equals(user.getUsername()) && password.equals(user.getPassword())
				&& user.getRole().equals(Role.FINANCE_MANAGER)) {
			// create a session
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Send them to the Finance Manager page
			reqDispatcher = req.getRequestDispatcher("finance.html");
			reqDispatcher.forward(req, resp);
			
		} else{
			reqDispatcher = req.getRequestDispatcher("index.html");
			reqDispatcher.include(req, resp);
			printWriter.print("<span style='color:red; text-align:center;'>"
					+ "Login failed</span>");
		}
	}

}
