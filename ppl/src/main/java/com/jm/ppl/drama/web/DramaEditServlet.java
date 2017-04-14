package com.jm.ppl.drama.web;

import java.io.File;
import java.io.IOException;

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

public class DramaEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DramaService dramaService;

	public DramaEditServlet() {
		dramaService = new DramaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");

		if (user.getAuthorizationId().equals(AuthConst.ADMIN_USER)
				|| user.getAuthorizationId().equals(AuthConst.OPERATOR_USER)) {

			String dramaId = request.getParameter("dramaId");

			DramaVO drama = dramaService.getOneDrama(dramaId);

			request.setAttribute("drama", drama);

			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/drama/edit.jsp");
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

			String dramaId = multipart.getParameter("dramaId");
			String dramaTitle = multipart.getParameter("dramaTitle");
			String dramaProducer = multipart.getParameter("dramaProducer");
			String dramaWriter = multipart.getParameter("dramaWriter");
			String dramaScreeningTime = multipart.getParameter("dramaScreeningTime");
			String dramaGenre = multipart.getParameter("dramaGenre");
			String dramaActor = multipart.getParameter("dramaActor");

			MultipartFile post = multipart.getFile("dramaPost");

			System.out.println("getfileName : " + post.getFileName());

			DramaVO drama = new DramaVO();

			if (post != null && post.getFileSize() > 0) {
				File dir = new File("D:\\ppl\\drama\\post\\" + dramaTitle + File.separator);
				dir.mkdirs();
				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
				drama.setDramaPost(post.getFileName());
			} else {
				DramaVO dramaTemp = dramaService.getOneDrama(dramaId);
				drama.setDramaPost(dramaTemp.getDramaPost());
			}

			drama.setDramaId(dramaId);
			drama.setDramaTitle(dramaTitle);
			drama.setDramaProducer(dramaProducer);
			drama.setDramaWriter(dramaWriter);
			drama.setDramaScreeningTime(dramaScreeningTime);
			drama.setDramaGenre(dramaGenre);
			drama.setDramaActor(dramaActor);

			boolean isSuccess = dramaService.renewOneDrama(drama);

			if (isSuccess) {
				response.sendRedirect("/ppl/drama/list");
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(404);
		}
	}

}
