package com.jm.ppl.items.web;

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
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.user.vo.UserVO;

public class DoItemNotLikeActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemsService itemService;   

    public DoItemNotLikeActionServlet() {
    	itemService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemId = request.getParameter("itemId");
		boolean isLikeItem = true;
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_"); 
		
		if( itemService.minusLikeCount(itemId) ) {
			isLikeItem = false;
		}
		
		userVO.setIsLike(isLikeItem);
		request.setAttribute("isLikeItem", isLikeItem);
		
		System.out.println(itemId);
		System.out.println(isLikeItem);
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("status", "success");
		map.put("itemId", itemId);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
