package com.jm.ppl.comment.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.comment.service.CommentService;
import com.jm.ppl.comment.service.CommentServiceImpl;

public class DoCommentDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;
	
    public DoCommentDeleteActionServlet() {
    	commentService = new CommentServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter("commentId");
		String itemId = commentService.getOneComment(commentId).getItemId();
		
		if(commentService.removeOneComment(commentId)){
			response.sendRedirect("/ppl/item/detail?itemId="+itemId);
		} else {
			response.sendError(500);
		}
	}

}
