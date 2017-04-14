package com.jm.ppl.admin.authorization.service;

import java.util.List;

import com.jm.ppl.admin.authorization.biz.AuthorBiz;
import com.jm.ppl.admin.authorization.biz.AuthorBizImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;

public class AuthorServiceImpl implements AuthorService{

	AuthorBiz authorBiz;
	public AuthorServiceImpl() {
		authorBiz = new AuthorBizImpl();
	}
	
	
	@Override
	public boolean inputNewAuthor(AuthorizationVO authorizationVO) {
		
		return authorBiz.inputNewAuthor(authorizationVO);
	}

	@Override
	public List<AuthorizationVO> selectAllUser(AuthorizationSearchVO authorizationSearchVO) {
		
		return authorBiz.selectAllUser(authorizationSearchVO);
	}

	@Override
	public boolean updateAuthorizationInfo(AuthorizationVO authorizationVO) {
		
		return authorBiz.updateAuthorizationInfo(authorizationVO);
	}

	@Override
	public boolean deleteOneAuthorization(String authorizationId) {
		
		return authorBiz.deleteOneAuthorization(authorizationId);
	}

}
