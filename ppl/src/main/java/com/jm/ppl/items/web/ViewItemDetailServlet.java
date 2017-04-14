package com.jm.ppl.items.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jm.ppl.comment.service.CommentService;
import com.jm.ppl.comment.service.CommentServiceImpl;
import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;
import com.jm.ppl.common.constants.AuthConst;
import com.jm.ppl.common.web.pager.ClassicPageExplorer;
import com.jm.ppl.common.web.pager.PageExplorer;
import com.jm.ppl.common.web.pager.Pager;
import com.jm.ppl.common.web.pager.PagerFactory;
import com.jm.ppl.itemPost.service.ItemPostService;
import com.jm.ppl.itemPost.service.ItemPostServiceImpl;
import com.jm.ppl.itemPost.vo.ItemPostVO;
import com.jm.ppl.items.service.ItemsService;
import com.jm.ppl.items.service.ItemsServiceImpl;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.user.vo.UserVO;

public class ViewItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsService itemsService;
	private CommentService commentService;
	private ItemPostService itemPostService;
	
    public ViewItemDetailServlet() {
    	itemsService = new ItemsServiceImpl();
    	commentService = new CommentServiceImpl();
    	itemPostService = new ItemPostServiceImpl();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String pageNo =  request.getParameter("pageNo");
//		Pager pager = PagerFactory.getPager(Pager.ORACLE);
//		pager.setPageNumber(pageNo);
		
//		ItemsSearchVO itemSearchVO = new ItemsSearchVO();
//		itemSearchVO.setPager(pager);
//		List<ItemsVO> itemList = itemsService.getAllItems(itemSearchVO);
//		
//		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
//		String pages = pageExplorer.getPagingList("pageNo", " (@)", "PREV", "NEXT", "searchForm");
		String itemId = request.getParameter("itemId");
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		CommentSearchVO commentSearch = new CommentSearchVO();
		commentSearch.setPager(pager);
		commentSearch.setItemId(itemId);
		List<CommentVO> commentList = commentService.getAllComments(commentSearch);
		System.out.println("commentList.size : " + commentList.size());
		
		request.setAttribute("commentList", commentList);
		
		List<ItemPostVO> postList = itemPostService.getAllPosts(itemId);
		request.setAttribute("postList", postList);
		
		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
		String pages = pageExplorer.getPagingList("pageNo", " (@)", "PREV", "NEXT", "searchForm");
		request.setAttribute("pager", pages);
		
		
		ItemsVO itemVO = itemsService.getOneItem(itemId);
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_"); 
		
		boolean isPriceUnknown = false;
		
		if ( itemVO.getItemPrice() == 0 ) {
			isPriceUnknown = true;
		}
		
		boolean isLikeItem = user.getIsLike();
		
		request.setAttribute("itemVO", itemVO);
//		request.setAttribute("itemList", itemList);
//		request.setAttribute("count", pager.getTotalArticleCount());
//		request.setAttribute("pager", pages);
		request.setAttribute("isLikeItem", isLikeItem);
		
		request.setAttribute("isNormalUser", user.getAuthorizationId().equals(AuthConst.NORMAL_USER));
		request.setAttribute("isAdminUser", user.getAuthorizationId().equals(AuthConst.ADMIN_USER));
		request.setAttribute("isOperatorUser", user.getAuthorizationId().equals(AuthConst.OPERATOR_USER));
		request.setAttribute("isPriceUnknown", isPriceUnknown);
		
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/item/detail.jsp");
		dis.forward(request, response);
	}

}




