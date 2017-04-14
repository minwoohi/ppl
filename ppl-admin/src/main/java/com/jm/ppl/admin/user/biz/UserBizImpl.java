package com.jm.ppl.admin.user.biz;

import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.admin.common.web.pager.Pager;
import com.jm.ppl.admin.user.dao.UserDao;
import com.jm.ppl.admin.user.dao.UserDaoImpl;
import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public class UserBizImpl implements UserBiz{

	UserDao userDao;
	public UserBizImpl(){
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean inputNewUser(UserVO userVO) {
		return userDao.insertNewUser(userVO) > 0;
	}

	@Override
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {
		
		int userCount = userDao.selectCountByUserId(userSearchVO);
		
		Pager pager = userSearchVO.getPager();
		pager.setTotalArticleCount(userCount);
		
		if(userCount == 0){
			return new ArrayList<UserVO>();
			
		}
		return userDao.selectAllUser(userSearchVO);
	}

	@Override
	public UserVO selectOneUser(String userId) {
		
		return userDao.selectOneUser(userId);
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) {
		
		return userDao.selectOneUser(userVO);
	}

	@Override
	public boolean updateUserInfo(UserVO userVO) {
		
		return userDao.updateUserInfo(userVO) > 0;
		
	}

	@Override
	public boolean deleteOneUser(String userId) {
		
		return userDao.deleteOneUser(userId) > 0;
	}

	@Override
	public boolean changeAuthorization(String[] authorizationId) {
		// TODO Auto-generated method stub
		return userDao.updateAthorizationId(authorizationId) > 0;
	}
	
	public SignVO viewSignUpUserByDate (int dateCont){
		
		/*SignVO signVO= userDao.selectSignDate(dateCont);
		System.out.println(signVO.getRegistDate());
		System.out.println(signVO.getCount());*/
		
		return userDao.selectSignDate(dateCont);
		
	}

	@Override
	public UserVO signInUser(UserVO user) {
		
		UserVO signInUser = userDao.selectOneUser(user);
		return signInUser;
	}

}
