package com.jm.ppl.admin.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserVO;


public class ViewSignUserByDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService userService;
    public ViewSignUserByDay() {
       userService = new UserServiceImpl();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if(user==null){
			response.sendRedirect("/ppl-admin/user/signIn");
		}
		else{
			
			if(user.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
				SignVO signVO = userService.viewSignUpUserByDate(0);
				String signDate = signVO.getRegistDate().substring(0 , 10);
				
				request.setAttribute("signdate" , signDate);
				request.setAttribute("signcount", signVO.getCount());
				
				
				for(int i=1 ; i < 7 ; i++){
					signVO = userService.viewSignUpUserByDate(-i);
					signDate = signVO.getRegistDate().substring(0 , 10);
					
					request.setAttribute("signdate" + i, signDate);
					request.setAttribute("signcount"+ i, signVO.getCount());
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/signdate.jsp");
				dispatcher.forward(request, response);
				
			}
			else{
				response.sendError(404);
			}
		}

	}
		

}
