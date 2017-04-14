package com.jm.ppl.actor.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.actor.service.ActorService;
import com.jm.ppl.actor.service.ActorServiceImpl;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.common.web.pager.PageExplorer;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewActorDetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActorService actorService;
	private ItemsService itemsService;
	
    public ViewActorDetailServlet() {
    	actorService = new ActorServiceImpl();
    	itemsService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		ItemsSearchVO itemSearchVO = new ItemsSearchVO();
		itemSearchVO.setPager(pager);
				
		String actorId = request.getParameter("actorId");
		itemSearchVO.setActorId(actorId);
		itemSearchVO.getPager().setPageNumber(pageNo);
		ActorVO actorVO = actorService.getOneActor(actorId);
		
		boolean isLikeActor = true;
		boolean isLogin = false;
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if( user == null ) {
			isLikeActor = false;
			isLogin = false;
		}
		else {
			isLogin = true;
			isLikeActor = user.getIsLike();
		}
		List<ItemsVO> itemList = itemsService.getAllitemsByIds(itemSearchVO);
	
		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
		String pages = pageExplorer.getPagingList("pageNo", " (@)", "PREV", "NEXT", "searchForm");
		
		
		request.setAttribute("actorVO", actorVO);
		request.setAttribute("itemList", itemList);
		request.setAttribute("count", itemSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pages);
		request.setAttribute("isLikeActor", isLikeActor);
		request.setAttribute("isLogin", isLogin);
		
		if ( user == null ) {
			request.setAttribute("isNormalUser", false);
			request.setAttribute("isAdminUser", false);
			request.setAttribute("isOperatorUser", false);
		}
		else {
			request.setAttribute("isNormalUser", user.getAuthorizationId().equals(AuthConst.NORMAL_USER));
			request.setAttribute("isAdminUser", user.getAuthorizationId().equals(AuthConst.ADMIN_USER));
			request.setAttribute("isOperatorUser", user.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/actor/detail.jsp");
		dispatcher.forward(request, response);
	}

}
