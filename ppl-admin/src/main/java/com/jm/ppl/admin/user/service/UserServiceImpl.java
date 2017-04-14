package com.jm.ppl.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jm.ppl.admin.authorization.biz.AuthorBiz;
import com.jm.ppl.admin.authorization.biz.AuthorBizImpl;
import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.user.biz.UserBiz;
import com.jm.ppl.admin.user.biz.UserBizImpl;
import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public class UserServiceImpl implements UserService{

	private UserBiz userBiz;
	private AuthorBiz authorBiz;
	public UserServiceImpl() {
	
		userBiz = new UserBizImpl();
		authorBiz = new AuthorBizImpl();
	}
	@Override
	public boolean inputNewUser(UserVO userVO) {
		
		return userBiz.inputNewUser(userVO);
	}
	@Override
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {

		return userBiz.selectAllUser(userSearchVO);
	}
	@Override
	public UserVO selectOneUser(String userId) {
	
		return userBiz.selectOneUser(userId);
	}
	@Override
	public UserVO selectOneUser(UserVO userVO) {
	
		return userBiz.selectOneUser(userVO);
	}
	@Override
	public boolean updateUserInfo(UserVO userVO) {
		
		UserVO tempVO = selectOneUser(userVO.getUserId());
		
		if(userVO.getAuthorizationId() != null && userVO.getAuthorizationId().length() > 0 ){
			
			tempVO.setAuthorizationId(userVO.getAuthorizationId());
		} 
		if(userVO.getRegistDate() != null && userVO.getRegistDate().length() > 0 ){
			
			tempVO.setRegistDate(userVO.getRegistDate());
		}
		if(userVO.getUserPassword() != null && userVO.getUserPassword().length() > 0 ){
			
			tempVO.setUserPassword(userVO.getUserPassword());
		}
			//권한정보가 있어 수정 했다면 각각의 경우에따라 
			
		
		return userBiz.updateUserInfo(tempVO);
	}
	@Override
	public boolean deleteOneUser(String userId) {
	
		return userBiz.deleteOneUser(userId);
	}
	@Override
	public Map<String, Object> getOneUserWithAuthorizations(String userId) {
		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(0);
		
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("user", userBiz.selectOneUser(userId));
		user.put("authorizations", authorBiz.selectAllUser(authorizationSearchVO));
		
		return user;
	}
	@Override
	public boolean changeAthorizationId(String[] authorizationId) {
		// TODO Auto-generated method stub
		return userBiz.changeAuthorization(authorizationId);
	}
	@Override
	public boolean updateAllAuthorization(String[] userArray, String toAuth, String fromAuth) {
		// TODO Auto-generated method stub
		UserVO userVO = null;
		for(String userId : userArray){
			userVO= new UserVO();
			userVO.setUserId(userId);
			userVO.setAuthorizationId(toAuth);
			updateUserInfo(userVO);
			
		}
		
		return true;
	}
	@Override
	public SignVO viewSignUpUserByDate(int dateCont) {
	
		return userBiz.viewSignUpUserByDate(dateCont);
	}
	@Override
	public UserVO signInUser(UserVO user) {
		return userBiz.signInUser(user);
	}


	
}
