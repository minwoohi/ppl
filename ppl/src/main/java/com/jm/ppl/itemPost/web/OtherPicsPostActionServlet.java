package com.jm.ppl.itemPost.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.common.web.DownloadUtil;
import com.jm.ppl.itemPost.service.ItemPostService;
import com.jm.ppl.itemPost.service.ItemPostServiceImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;

public class OtherPicsPostActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemPostService itemPostService;

	public OtherPicsPostActionServlet() {
		itemPostService = new ItemPostServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemPostId = request.getParameter("itemPostId");

		ItemPostVO item = itemPostService.getOnePost(itemPostId);
		String postPath = "D:\\ppl\\item\\post\\";

		postPath += item.getItemId();

		DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
		downloadUtil.download(request, response, item.getPostName(), item.getPostName());
	}

}
