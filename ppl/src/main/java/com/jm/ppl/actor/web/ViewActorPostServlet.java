package com.jm.ppl.actor.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.web.DownloadUtil;

public class ViewActorPostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActorService actorService;
	
    public ViewActorPostServlet() {
        actorService = new ActorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actorId = request.getParameter("actorId");
		
		ActorVO actorVO = actorService.getOneActor(actorId);
		String postPath = "D:\\actor\\post\\";
	
		postPath += actorVO.getActorName();
		
		DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
		downloadUtil.download(request, response, actorVO.getActorPost(), actorVO.getActorPost());
		
	}

}
