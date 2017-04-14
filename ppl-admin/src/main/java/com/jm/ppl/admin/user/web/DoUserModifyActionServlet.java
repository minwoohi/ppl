package com.jm.ppl.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class DoUserModifyActionServlet
 */
public class DoUserModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService;
	
    public DoUserModifyActionServlet() {
        userService = new UserServiceImpl();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String registDate = request.getParameter("registDate");
		String password = request.getParameter("userPassword");
		String authorizationId = request.getParameter("authorizationId");
		String userId = request.getParameter("userId");
		
		
		
		
		
		
		
		
		UserVO userVO = new UserVO();
		//널은 널인채로 있으면 있는데로 
		
		userVO.setRegistDate(registDate);
		userVO.setUserPassword(password);
		userVO.setAuthorizationId(authorizationId);
		userVO.setUserId(userId);
		
		boolean isSuccess = userService.updateUserInfo(userVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", isSuccess ? "success" : "fail");
		
		Gson gson =new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
				
	
		
	}
}
