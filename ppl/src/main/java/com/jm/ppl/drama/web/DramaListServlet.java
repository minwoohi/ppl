package com.jm.ppl.drama.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.common.web.pager.PageExplorer;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;
import com.jm.ppl.user.vo.UserVO;


public class DramaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DramaService dramaService;
	
    public DramaListServlet() {
    	dramaService = new DramaServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("_USER_");
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		DramaSearchVO dramaSearch = new DramaSearchVO();
		dramaSearch.setPager(pager);
		List<DramaVO> dramaList = dramaService.getAllDramas(dramaSearch);
		
		PageExplorer pageEx = new ClassicPageExplorer(pager);
		String page = pageEx.getPagingList("pageNo", "[@]", "Prev", "Next", "searchForm");
		
		request.setAttribute("dramaList", dramaList);
		request.setAttribute("count", pager.getTotalArticleCount());
		request.setAttribute("pager", page);
		

		if(user == null){
			request.setAttribute("isNormalUser", false);
			request.setAttribute("isAdminUser", false);
			request.setAttribute("isOperatorUser", false);
			request.getRequestDispatcher("/user/signIn").forward(request, response);
		}
		else{
		request.setAttribute("isNormalUser", user.getAuthorizationId().equals(AuthConst.NORMAL_USER));
		request.setAttribute("isAdminUser", user.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		request.setAttribute("isOperatorUser", user.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		}
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/drama/list.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
