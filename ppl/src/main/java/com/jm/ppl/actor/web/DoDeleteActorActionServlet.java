package com.jm.ppl.actor.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.user.vo.UserVO;

public class DoDeleteActorActionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActorService actorService;
	
    public DoDeleteActorActionServlet() {
       actorService = new ActorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			String actorId = request.getParameter("actorId");
					
			if ( actorService.removeActor(actorId) ) {
				response.sendRedirect("/ppl/actor/list");
			}
		}
		else {
			response.sendError(404);
		}
	}

}
