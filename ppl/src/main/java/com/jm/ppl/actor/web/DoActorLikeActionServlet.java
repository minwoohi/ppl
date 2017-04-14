package com.jm.ppl.actor.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class DoActorLikeActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ActorService actorService;
    
    public DoActorLikeActionServlet() {
    	actorService = new ActorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actorId = request.getParameter("actorId");
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_"); 
		
		if ( user == null ) {
			
			response.sendRedirect("/ppl/user/signIn");
		}
		else {
		
			boolean isLikeActor = actorService.plusLikeCount(actorId);
			
			user.setIsLike(isLikeActor);
			request.setAttribute("isLikeActor", isLikeActor);
			
			//System.out.println(itemId);
			//System.out.println(isLikeItem);
			
			Map<String, Object> map = new HashMap<String,Object>();
			
			map.put("status", "success");
			map.put("actorId", actorId);
			
			Gson gson = new Gson();
			String json = gson.toJson(map);
			
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}

}
