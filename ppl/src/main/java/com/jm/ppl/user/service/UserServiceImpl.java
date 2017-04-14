package com.jm.ppl.user.service;

import com.jm.ppl.user.biz.UserBiz;
import com.jm.ppl.user.biz.UserBizImpl;
import com.jm.ppl.user.vo.UserVO;

public class UserServiceImpl implements UserService{

	UserBiz userBiz;
	
	public UserServiceImpl() {

		userBiz = new UserBizImpl();
	
	}

	@Override
	public boolean signUpUser(UserVO newUser) {
		return userBiz.signUpUser(newUser);
	}

	@Override
	public UserVO signInUser(UserVO user) {
		return userBiz.signInUser(user);
	}

	@Override
	public boolean isDuplicatedUserId(String userId) {
		return userBiz.isDuplicatedUserId(userId);
	}
}
