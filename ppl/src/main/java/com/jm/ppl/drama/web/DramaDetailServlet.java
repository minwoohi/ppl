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
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.drama.vo.DramaVO;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;


public class DramaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DramaService dramaService;
	private ItemsService itemService;
	
    public DramaDetailServlet() {
    	dramaService = new DramaServiceImpl();
    	itemService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dramaId = request.getParameter("dramaId");
		
		DramaVO drama = dramaService.getOneDrama(dramaId);
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		ItemsSearchVO itemSearch = new ItemsSearchVO();
		itemSearch.setDramaId(dramaId);
		List<ItemsVO> itemList = itemService.getAllitemsByIds(itemSearch);
		
		request.setAttribute("drama", drama);
		request.setAttribute("itemList", itemList);
		
		request.setAttribute("isNormalUser", user.getAuthorizationId().equals(AuthConst.NORMAL_USER));
		request.setAttribute("isAdminUser", user.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		request.setAttribute("isOperatorUser", user.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/drama/detail.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

}
