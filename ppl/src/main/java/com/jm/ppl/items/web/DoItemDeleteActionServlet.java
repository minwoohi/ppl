package com.jm.ppl.items.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class DoItemDeleteActionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ItemsService itemService;
       
    public DoItemDeleteActionServlet() {
    	itemService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			String itemId = request.getParameter("itemId");
			String dramaId = request.getParameter("dramaId");
			String movieId = request.getParameter("movieId");
			String actorId = request.getParameter("actorId");
			
			System.out.println(dramaId);
			System.out.println(movieId);
			System.out.println(actorId);
			
			if ( dramaId.length() > 5 ) {
				if ( itemService.removeItem(itemId) ) {
					response.sendRedirect("/ppl/drama/detail?dramaId=" + dramaId);
				}
			}
			else if ( movieId.length() > 5 ) {
				if ( itemService.removeItem(itemId) ) {
					response.sendRedirect("/ppl/movie/detail?movieId=" + movieId);
				}
			}
			else if ( actorId.length() > 5 ) {
				if ( itemService.removeItem(itemId) ) {
					response.sendRedirect("/ppl/actor/detail?actorId=" + actorId);
				}
			}
		
		}
		else {
			response.sendError(404);
		}
	}

}
