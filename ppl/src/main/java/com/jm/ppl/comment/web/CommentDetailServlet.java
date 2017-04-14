package com.jm.ppl.comment.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.comment.service.CommentService;
import com.jm.ppl.comment.service.CommentServiceImpl;
import com.jm.ppl.comment.vo.CommentVO;

public class CommentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentService commentService;
	
    public CommentDetailServlet() {
    	commentService = new CommentServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter("commentId");
		CommentVO comment = commentService.getOneComment(commentId);
		
		request.setAttribute("comment", comment);
		System.out.println("userId : " + comment.getUserId());
		
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/comment/detail.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
