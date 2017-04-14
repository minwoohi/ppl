package com.jm.ppl.user.biz;

import com.jm.ppl.user.dao.UserDao;
import com.jm.ppl.user.dao.UserDaoImpl;
import com.jm.ppl.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	private UserDao userDao;
	
	public UserBizImpl(){
		userDao = new UserDaoImpl();	
	}

	@Override
	public boolean signUpUser(UserVO newUser) {

		boolean isSuccess = userDao.insertNewUser(newUser)>0;
		
		return isSuccess;
	}

	@Override
	public UserVO signInUser(UserVO user) {
		
		UserVO signInUser = userDao.selectOneUser(user);
		return signInUser;
	}

	@Override
	public boolean isDuplicatedUserId(String userId) {
		return userDao.selectCountByUserId(userId)>0;
	}
}
