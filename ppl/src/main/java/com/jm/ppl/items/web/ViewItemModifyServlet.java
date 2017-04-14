package com.jm.ppl.items.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewItemModifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ItemsService itemService;
    
    public ViewItemModifyServlet() {
    	itemService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			String itemId = request.getParameter("itemId");
			
			ItemsVO itemVO = itemService.getOneItem(itemId);
			
			request.setAttribute("itemVO", itemVO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/item/modify.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendError(404);
		}
	}

}
