package com.jm.ppl.admin.authorization.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class ViewAuthorizationManage
 */
public class ViewAuthorizationManage extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    
     public ViewAuthorizationManage() {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		
		if(user == null ){
			response.sendRedirect("/ppl-admin/user/signIn");
		}
		else{
			if(user.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/authorization/manage.jsp");
			dispatcher.forward(request, response);
			}
			else {
				response.sendError(404);
			}
		}

	}
}
