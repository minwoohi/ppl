package com.jm.ppl.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.user.service.UserService;
import com.jm.ppl.user.service.UserServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService;
	
	public ViewSignUpServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("s");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		
		if(userId == null || userId.length()==0){
			response.sendRedirect("/ppl/user/signUp?errorCode=0");
			return;
			}
		if(userPassword == null || userPassword.length()==0){
			response.sendRedirect("/ppl/user/signUp?errorCode=1");
			return;
		}
		if(userName==null || userName.length() == 0){
			response.sendRedirect("/ppl/user/signUp?errorCode=2");
			return;
		}
		if(userService.isDuplicatedUserId(userId)){
			response.sendRedirect("/ppl/user/signUp?errorCode=3");
			return;
		}
		
		UserVO user = new UserVO();
		
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		
		if(userService.signUpUser(user)){
			System.out.println("유저등록을 완료하였습니다.");
			response.sendRedirect("/ppl/user/signIn");
		}else{
			System.out.println("유저등록을 실패하였습니다.");
			response.sendRedirect("/ppl/user/signUp");
		}
	}
}


