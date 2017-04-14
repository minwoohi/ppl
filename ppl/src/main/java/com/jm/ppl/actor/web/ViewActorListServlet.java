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
import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.common.web.pager.PageExplorer;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.user.vo.UserVO;

public class ViewActorListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActorService actorService;
	
    public ViewActorListServlet() {
    	actorService = new ActorServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("_USER_");
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		ActorSearchVO actorSearchVO = new ActorSearchVO();
		actorSearchVO.setPager(pager);
		List<ActorVO> actorList = actorService.getAllActors(actorSearchVO);
		
		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
		String pages = pageExplorer.getPagingList("pageNo", " (@)", "PREV", "NEXT", "searchForm");
		
		
		request.setAttribute("actorList", actorList);
		request.setAttribute("count", pager.getTotalArticleCount());
		request.setAttribute("pager", pages);
		
		if ( user == null ) {
			request.setAttribute("isNormalUser", false);
			request.setAttribute("isAdminUser", false);
			request.setAttribute("isOperatorUser", false);
			request.getRequestDispatcher("/user/signIn").forward(request, response);
		}
		else {
			request.setAttribute("isNormalUser", user.getAuthorizationId().equals(AuthConst.NORMAL_USER));
			request.setAttribute("isAdminUser", user.getAuthorizationId().equals(AuthConst.ADMIN_USER));
			request.setAttribute("isOperatorUser", user.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/actor/list.jsp");
		dispatcher.forward(request, response);
	}

}
