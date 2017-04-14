package com.jm.ppl.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.jm.ppl.admin.user.vo.UserVO;

/**
 * Servlet implementation class ViewAllAuthorization
 */
public class ViewAllAuthorization extends HttpServlet {
private static final long serialVersionUID = 1L;
    
	private AuthorService authorService;
	
    public ViewAllAuthorization() {
       authorService = new AuthorServiceImpl();
    }




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		if(user.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
		String pageNo = request.getParameter("pageNo");
		if( pageNo == null || pageNo.length() == 0){
			pageNo="0";
		}
		
		AuthorizationSearchVO searchVO = new AuthorizationSearchVO();
		searchVO.getPager().setPageNumber(pageNo);
		List<AuthorizationVO> authorList = authorService.selectAllUser(searchVO);
		
		StringBuffer json = new StringBuffer();
		json.append("  {  ");
		json.append("  \"status\" : \"success\",  ");
		json.append("  \"size\" : " + authorList.size() + ",");
		json.append("  \"pageNo\" : " + pageNo + ",");
	    json.append("  \"authorizations\": [  ");
	    String authorizationData="";
	    for (AuthorizationVO authorizationVO : authorList) {
			authorizationData += //계속더한다 여러개 이어준다 그러면 마지막에 콤마는 빼줘야됨
			String.format(" { \"authorizationId\": \"%s\",\"authorizationName\" : \"%s\", \"parentAuthorizationId\" : \"%s\" },",
			    authorizationVO.getAuthorizationId()
			   ,authorizationVO.getAuthorizationName()
			   ,authorizationVO.getParentAuthorizationId());
		}
	    
	     if(authorizationData.length() > 0 ){
	    	 authorizationData = authorizationData.substring(0 , authorizationData.length() - 1);//글자를 잘라줌어디부터 자를래 0부터 시작 ~ 어디까지 자를래
	     } 
	    
	     json.append(authorizationData);
	     json.append("  ]  ");
		 json.append("  }  ");
		
		PrintWriter writer = response.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();

	}else{
		response.sendError(404);
	}
	}

}
