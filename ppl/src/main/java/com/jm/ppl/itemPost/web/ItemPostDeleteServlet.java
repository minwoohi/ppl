package com.jm.ppl.itemPost.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.itemPost.service.ItemPostService;
import com.jm.ppl.itemPost.service.ItemPostServiceImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;

public class ItemPostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemPostService itemPostService;
	
    public ItemPostDeleteServlet() {
    	itemPostService = new ItemPostServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] itemPostList = request.getParameterValues("checkbox");
		String itemId = request.getParameter("itemId");
		
		
		System.out.println("itemPostDelete 서블릿");
		System.out.println("itemPostList.size : " + itemPostList.length);
		System.out.println("itemId : " + itemId);
		
		for(String itemPostId : itemPostList){
			if(itemPostService.removeOnePost(itemPostId)){
				continue;
			} else {
				response.sendError(500);
			}
		}
		response.sendRedirect("/ppl/item/detail?itemId=" + itemId);
	}

}
