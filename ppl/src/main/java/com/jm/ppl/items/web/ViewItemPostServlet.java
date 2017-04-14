package com.jm.ppl.items.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.common.web.DownloadUtil;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsVO;

public class ViewItemPostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ItemsService itemsService;
	
    public ViewItemPostServlet() {
    	itemsService = new ItemsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemId = request.getParameter("itemId");
		
		ItemsVO itemsVO = itemsService.getOneItem(itemId);
		String postPath = "D:\\items\\post\\";
		
		postPath += itemsVO.getItemName();
		
		DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
		downloadUtil.download(request, response, itemsVO.getItemPost(), itemsVO.getItemPost());
		
	}

}
