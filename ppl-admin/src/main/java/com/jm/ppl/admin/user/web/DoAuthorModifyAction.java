package com.jm.ppl.admin.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.UserVO;


public class DoAuthorModifyAction extends HttpServlet {
private static final long serialVersionUID = 1L;
   
	
	private UserService userService;
    public DoAuthorModifyAction() {
     
    	userService = new UserServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String auth1 = request.getParameter("auth1");
		String auth2 = request.getParameter("auth2");
		String[] authorizationId = { auth1 , auth2 };
		
		if(userService.changeAthorizationId(authorizationId)){
			response.sendRedirect("/ppl-admin/user/list");
			
		}
		
		
	}

}
