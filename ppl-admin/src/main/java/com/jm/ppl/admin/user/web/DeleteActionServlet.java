package com.jm.ppl.admin.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;


public class DeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService userService;
    public DeleteActionServlet() {
    	userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId = request.getParameter("userId");
		
		
		if(userService.deleteOneUser(userId)){
			response.sendRedirect("/ppl-admin/user/list");
		}
		
		
		
	}

}
