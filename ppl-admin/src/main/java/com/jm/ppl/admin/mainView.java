package com.jm.ppl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.vo.UserVO;


public class mainView extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public mainView() {
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main/main.jsp");
			dispatcher.forward(request, response);
		
	}
}