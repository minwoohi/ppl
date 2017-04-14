package com.jm.ppl.admin.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.admin.authorization.service.AuthorService;
import com.jm.ppl.admin.authorization.service.AuthorServiceImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;
import com.jm.ppl.admin.common.constants.AuthConst;
import com.jm.ppl.admin.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.admin.common.web.pager.PageExplorer;
import com.jm.ppl.admin.user.service.UserService;
import com.jm.ppl.admin.user.service.UserServiceImpl;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class ViewListServlet
 */
public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService service;
	private AuthorService authorService;
    
	public ViewListServlet() {
      
		authorService = new AuthorServiceImpl();
		service = new UserServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
	
		if(user == null ){
			response.sendRedirect("/ppl-admin/user/signIn");
		}
		else {
			if(user.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
			
			String pageNo = request.getParameter("pageNo");
		
			
			UserSearchVO userSearchVO = new UserSearchVO();
			
			
			AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
			userSearchVO.getPager().setPageNumber(pageNo);
			authorizationSearchVO.getPager().setPageNumber(pageNo);
			List<UserVO> userList = service.selectAllUser(userSearchVO);
			List<AuthorizationVO> authList = authorService.selectAllUser(authorizationSearchVO);
			PageExplorer pager = new ClassicPageExplorer(userSearchVO.getPager());
			
			
	
			
			request.setAttribute("authList", authList);
			request.setAttribute("userList", userList);
		
			request.setAttribute("userCount", userSearchVO.getPager().getTotalArticleCount());
			request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "prev", "next", "searchForm"));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/list.jsp");
			dispatcher.forward(request, response);
			}
			else{
				response.sendError(404);
			}
		
		}
}
}
