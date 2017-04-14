package com.jm.ppl.like.web;

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
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.like.service.LikeService;
import com.jm.ppl.like.service.LikeServiceImpl;
import com.jm.ppl.like.vo.LikeVO;
import com.jm.ppl.movie.service.MovieService;
import com.jm.ppl.movie.service.MovieServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class DoLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LikeService likeService;
    private ActorService actorService;
    private DramaService dramaService;
    private MovieService movieService;
    private ItemsService itemService;
    
	
    public DoLikeServlet() {
    	likeService = new LikeServiceImpl();
    	actorService = new ActorServiceImpl();
    	dramaService = new DramaServiceImpl();
    	movieService = new MovieServiceImpl();
    	itemService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String targetId = "";
		
		targetId = request.getParameter("targetId");
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		String userId = user.getUserId();
		
		LikeVO like = likeService.getOneLike(userId, targetId);
		LikeVO likeNew = new LikeVO();
		likeNew.setTgtId(targetId);
		likeNew.setUserId(userId);
		
		
		// 좋아요 안되어있을 때
		if( like == null ) {
			if ( likeService.addLike(likeNew) ) {
				Map<String, Object> map = new HashMap<String,Object>();
				
				int likeCount = likeService.countLikeByTargetId(targetId);
				
				if ( actorService.setLike(likeCount, targetId) ) {
					System.out.println("액터 좋아요 셋팅");
				}
				else if ( dramaService.setLike(likeCount, targetId)  ) {
					System.out.println("드라마 좋아요 셋팅");
				}
				else if ( movieService.setLike(likeCount, targetId)  ) {
					System.out.println("무비 좋아요 셋팅");
				}
				else if ( itemService.setLike(likeCount, targetId)  ) {
					System.out.println("아이템 좋아요 셋팅");
				}
				else {
					System.out.println("이놈은 머냐");
				}
				
				map.put("status", "success");
				map.put("likeCount", likeCount);
				//map.put("targetId", targetId);
				
				Gson gson = new Gson();
				String json = gson.toJson(map);
				
				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();
				writer.close();
			}
			else {
				System.out.println("좋아요 실패");
			}
		}
		
		// 좋아요 되어있을 때
		else {
			if ( likeService.removeLike(userId, targetId) ) {
				Map<String, Object> map = new HashMap<String,Object>();
				
				int likeCount = likeService.countLikeByTargetId(targetId);
				
				if ( actorService.setLike(likeCount, targetId) ) {
					System.out.println("액터 좋아요 셋팅");
				}
				else if ( dramaService.setLike(likeCount, targetId)  ) {
					System.out.println("드라마 좋아요 셋팅");
				}
				else if ( movieService.setLike(likeCount, targetId)  ) {
					System.out.println("무비 좋아요 셋팅");
				}
				else if ( itemService.setLike(likeCount, targetId)  ) {
					System.out.println("아이템 좋아요 셋팅");
				}
				else {
					System.out.println("이놈은 머냐");
				}
				
				
				map.put("status", "success");
				map.put("likeCount", likeCount);
				//map.put("targetId", targetId);
				
				Gson gson = new Gson();
				String json = gson.toJson(map);
				
				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();
				writer.close();
			}
			else {
				System.out.println("좋아요 취소 실패");
			}
		}
		
		
		
	}

}
