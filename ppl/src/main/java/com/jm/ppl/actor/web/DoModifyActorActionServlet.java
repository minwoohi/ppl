package com.jm.ppl.actor.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.user.vo.UserVO;

public class DoModifyActorActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActorService actorService;
       
    public DoModifyActorActionServlet() {
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
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
			
			String actorId = multipart.getParameter("actorId");
			String actorName = multipart.getParameter("actorName");
			String actorSex = multipart.getParameter("actorSex");
			String actorBirth = multipart.getParameter("actorBirth");
			String actorHeight = multipart.getParameter("actorHeight");
			String actorWeight = multipart.getParameter("actorWeight");
			String actorEntertainment = multipart.getParameter("actorEntertainment");	
			String postFileName = "";
			
			MultipartFile post = multipart.getFile("post");
			if( post != null && post.getFileSize() > 0 ) {
				postFileName = post.getFileName();
				
				File dir = new File("D:\\actor\\post\\" + actorName );
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}
			
			ActorVO actorVO = new ActorVO();
			actorVO.setActorId(actorId);
			actorVO.setActorName(actorName);
			actorVO.setActorSex(actorSex);
			actorVO.setActorBirth(actorBirth);
			actorVO.setActorHeight(actorHeight);
			actorVO.setActorWeight(actorWeight);
			actorVO.setActorEntertainment(actorEntertainment);
			actorVO.setActorPost(postFileName);
			actorVO.setActorBirth(actorBirth);
			
			if ( actorService.modifyActor(actorVO) ) {
				StringBuffer script = new StringBuffer();
				script.append("<script type='text/javascript'>");
				script.append("		opener.location.reload();");
				script.append(" 	self.close();");
				script.append("</script>");
				
				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();
			}
			else {
				response.sendRedirect("/ppl/actor/modify?actorId=" + actorId);
			}
		}
		else {
			response.sendError(404);
		}
	}

}
