package com.jm.ppl.itemPost.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.MultipartHttpServletRequest;
import com.jm.ppl.common.web.MultipartHttpServletRequest.MultipartFile;
import com.jm.ppl.itemPost.service.ItemPostService;
import com.jm.ppl.itemPost.service.ItemPostServiceImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;
import com.jm.ppl.user.vo.UserVO;

public class ItemPostWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemPostService itemPostService;
	
    public ItemPostWriteServlet() {
    	itemPostService = new ItemPostServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/itemPost/writePost.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");

		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String itemId = multipart.getParameter("itemId");
			
			MultipartFile post = multipart.getFile("post");

			System.out.println("itemId : " + itemId);
			System.out.println("fileName : " + post.getFileName());
			if (post != null && post.getFileSize() > 0) {
				File dir = new File("D:\\ppl\\item\\post\\" + itemId + File.separator);
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}

			ItemPostVO item = new ItemPostVO();
			
			item.setItemId(itemId);
			item.setPostName(post.getFileName());

			if (itemPostService.addOnePost(item)) {
				// response.sendRedirect("/ppl/drama/list");
				StringBuffer script = new StringBuffer();
				script.append("<script type='text/javascript'>");
				script.append(" opener.location.reload();");
				script.append("self.close();"); // 스스로 닫는 쿼리문.
				script.append("</script>"); // 작성해

				PrintWriter writer = response.getWriter(); // resopnse 객체에 담는다.
				writer.write(script.toString()); // 버퍼에 담긴 상태
				writer.flush(); // 버퍼 내용 흘려보내기
				writer.close(); // 닫기
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
	}

}
