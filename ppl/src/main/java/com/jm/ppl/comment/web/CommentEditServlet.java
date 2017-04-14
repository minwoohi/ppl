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
public class CommentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;
	
    public CommentEditServlet() {
    	commentService = new CommentServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter("commentId");
		
		CommentVO comment = commentService.getOneComment(commentId);
		String content = comment.getComment();
		content = content.replaceAll("<br/>", "\n");
		
		comment.setComment(content);
		
		request.setAttribute("comment", comment);
		
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/comment/edit.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		String commentId = request.getParameter("commentId");
		
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");
		
		CommentVO comment = new CommentVO();
		
		comment.setComment(content);
		comment.setCommentId(commentId);
		
		if(commentService.renewOneComment(comment)){
			response.sendRedirect("/ppl/comment/detail?commentId="+commentId);
		} else {
			response.sendError(500);
		}
	}

}
