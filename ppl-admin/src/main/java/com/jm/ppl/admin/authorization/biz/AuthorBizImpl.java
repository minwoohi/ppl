package com.jm.ppl.admin.authorization.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.admin.authorization.dao.AuthorDao;
import com.jm.ppl.admin.authorization.dao.AuthorDaoImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;

public class AuthorBizImpl implements AuthorBiz{

	private AuthorDao authorDao;
	public AuthorBizImpl() {
		authorDao = new AuthorDaoImpl();
		
	}
	
	@Override
	public boolean inputNewAuthor(AuthorizationVO authorizationVO) {
		String id =authorDao.generateNewAuthorizationId();
		authorizationVO.setAuthorizationId(id); 
		return authorDao.insertNewAuthorization(authorizationVO) > 0;
	}

	@Override
	public List<AuthorizationVO> selectAllUser(AuthorizationSearchVO authorizationSearchVO) {
		int authorizationCount = authorDao.selectAllAuthorizationCount(authorizationSearchVO);
		
		authorizationSearchVO.getPager().setTotalArticleCount(authorizationCount);
		
		if(authorizationCount == 0 ){
		 return new ArrayList<AuthorizationVO>();
		}
		
		
		return authorDao.selectAllauthorization(authorizationSearchVO);
	}

	@Override
	public boolean updateAuthorizationInfo(AuthorizationVO authorizationVO) {
		
		return authorDao.updateAuthorization(authorizationVO) >0;
	}

	@Override
	public boolean deleteOneAuthorization(String authorizationId) {
		
		return authorDao.deleteOneAuthorization(authorizationId) > 0;
	}

}
