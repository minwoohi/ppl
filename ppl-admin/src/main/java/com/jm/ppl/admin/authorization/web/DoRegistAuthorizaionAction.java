package com.jm.ppl.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jm.ppl.admin.authorization.service.AuthorService;
import com.jm.ppl.admin.authorization.service.AuthorServiceImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;

/**
 * Servlet implementation class DoRegistAuthorizaionAction
 */
public class DoRegistAuthorizaionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private AuthorService authorService;
    public DoRegistAuthorizaionAction() {
        authorService = new AuthorServiceImpl();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);//url로 접근시 그런페이지 없다
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//등록만 시키는 ajax는 doGet에 하지않는다
		
		String  authorizationName = request.getParameter("authorizationName");
		String parentAuthorizationId = request.getParameter("parentAuthorizationId");//parentAuthorizationId
		
		AuthorizationVO authorizationVO = new AuthorizationVO();
		authorizationVO.setAuthorizationName(authorizationName);
		authorizationVO.setParentAuthorizationId(parentAuthorizationId);
		System.out.println(parentAuthorizationId);
		
		boolean isSuccess = authorService.inputNewAuthor(authorizationVO);
		//주소를 넘기고 있다 그래서 리턴을 VO로 해줄 필요없다
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("status","suceess");
		map.put("authorization", authorizationVO);
				//편하게 json만들어줌
		Gson gson =new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}


}
