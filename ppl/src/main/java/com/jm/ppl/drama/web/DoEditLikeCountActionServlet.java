package com.jm.ppl.drama.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.drama.vo.DramaVO;

public class DoEditLikeCountActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DramaService dramaService;
    
	public DoEditLikeCountActionServlet() {
		dramaService = new DramaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dramaId = request.getParameter("dramaId");
		
		System.out.println("editLikeCountServlet");
		
		
		DramaVO drama = dramaService.getOneDrama(dramaId);
		System.out.println("likeCount : " + drama.getDramaLikeCount());
		drama.setDramaLikeCount(drama.getDramaLikeCount() + 1);
		
		boolean isSuccess = dramaService.renewOneDrama(drama);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", isSuccess ? "success" : "fail");
		map.put("likeCount", drama.getDramaLikeCount());

		Gson gson = new Gson();
		String json = gson.toJson(map);

		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}

}
