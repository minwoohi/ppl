package com.jm.ppl.admin.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;

/**
 * Servlet implementation class DoSelectModifyActionServlet
 */
public class DoSelectModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService;
    public DoSelectModifyActionServlet() {
       userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] authorizationIds = request.getParameterValues("chk-info");
		String	toAuth = request.getParameter("auth3");
	
		
		if(userService.updateAllAuthorization(authorizationIds, toAuth, null)){
			response.sendRedirect("/ppl-admin/user/list");
		}
		
		
	}

}
