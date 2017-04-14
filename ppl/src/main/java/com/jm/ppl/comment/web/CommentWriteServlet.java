package com.jm.ppl.comment.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jm.ppl.comment.service.CommentService;
import com.jm.ppl.comment.service.CommentServiceImpl;
import com.jm.ppl.comment.vo.CommentVO;

public class CommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentService commentService;
	
    public CommentWriteServlet() {
    	commentService = new CommentServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/comment/write.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contents = request.getParameter("comment");
		
		contents = contents.replaceAll("\n", "<br/>");
		contents = contents.replaceAll("\r", "");
		
		String itemId = request.getParameter("itemId");
		String userId = request.getParameter("userId");
		
	
		CommentVO comment = new CommentVO();
		comment.setComment(contents);
		comment.setItemId(itemId);
		comment.setUserId(userId);
		
		if(commentService.addOneComment(comment)){
			
			String url = "/ppl/item/detail?itemId=" + itemId;
			/*String url = request.getHeader("referer");*/
			
			response.sendRedirect(url);
				
			
				/*StringBuffer script = new StringBuffer();
				script.append("<script type='text/javascript'>");
				script.append("		opener.location.reload();");
				script.append(" 	self.close();");
				script.append("</script>");
				
				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();*/
				
		} else {
			response.sendError(500);
		}
	}

}
