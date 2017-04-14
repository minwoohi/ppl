package com.jm.ppl.drama.web;

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
import com.jm.ppl.drama.service.DramaService;
import com.jm.ppl.drama.service.DramaServiceImpl;
import com.jm.ppl.drama.vo.DramaVO;
import com.jm.ppl.user.vo.UserVO;

public class DramaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DramaService dramaService;

	public DramaWriteServlet() {
		dramaService = new DramaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");

		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/drama/write.jsp");
			dis.forward(request, response);
		} else {
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");

		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String dramaTitle = multipart.getParameter("dramaTitle");
			String dramaProducer = multipart.getParameter("dramaProducer");
			String dramaWriter = multipart.getParameter("dramaWriter");
			String dramaScreeningTime = multipart.getParameter("dramaScreeningTime");
			String dramaGenre = multipart.getParameter("dramaGenre");
			String dramaActor = multipart.getParameter("dramaActor");

			MultipartFile post = multipart.getFile("dramaPost");

			if (post != null && post.getFileSize() > 0) {
				File dir = new File("D:\\ppl\\drama\\post\\" + dramaTitle + File.separator);
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}

			DramaVO drama = new DramaVO();

			drama.setDramaTitle(dramaTitle);
			drama.setDramaProducer(dramaProducer);
			drama.setDramaWriter(dramaWriter);
			drama.setDramaScreeningTime(dramaScreeningTime);
			drama.setDramaGenre(dramaGenre);
			drama.setDramaActor(dramaActor);
			drama.setDramaPost(post.getFileName());

			if (dramaService.addOneDrama(drama)) {
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
