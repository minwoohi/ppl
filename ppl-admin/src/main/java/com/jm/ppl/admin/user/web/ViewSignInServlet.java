package com.jm.ppl.admin.user.web;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.UserVO;



public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    public ViewSignInServlet() {
    	userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/user/signIn.jsp");
		dis.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
	
		UserVO user = new UserVO();
		
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		
		user = userService.signInUser(user);

		if(user==null){
			response.sendRedirect("/ppl-admin/user/signIn");
		}else if(user.getAuthorizationId().equals(AuthConst.NORMAL_USER)){
			
			
			response.sendError(404);
		}
		else if(user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)){
			
			response.sendError(404);
		}
		else if(user.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
			HttpSession session = request.getSession();
			
			session.setAttribute("_USER_", user);
			
			response.sendRedirect("/ppl-admin/user/list");
		}
	}

}
