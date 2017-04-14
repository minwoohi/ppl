package com.jm.ppl.admin.likecount.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.likecount.service.LikeCountService;
import com.jm.ppl.admin.likecount.service.LikeCountServiceImpl;
import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class ViewRankingServlet
 */
public class ViewRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    LikeCountService countService;
    public ViewRankingServlet() {
      countService = new LikeCountServiceImpl();
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
				
				List<ActorVO> actorList = countService.viewLikeCount();
				request.setAttribute("actorList", actorList);
				
				List<DramaVO> dramaList = countService.viewDramaLikeCount();
				request.setAttribute("dramaList", dramaList);
				
				List<MovieVO> movieList = countService.viewMovieLikeCount();
				request.setAttribute("movieList", movieList);
				
				List<ItemVO> itemList = countService.viewItemLikeCount();
				request.setAttribute("itemList", itemList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/likecount/ranking.jsp");
				dispatcher.forward(request, response);
			}
			
			else{
				response.sendError(404);
			}
		}
		
	}

}
