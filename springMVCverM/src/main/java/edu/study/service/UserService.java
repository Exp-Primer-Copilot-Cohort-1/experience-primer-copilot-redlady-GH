package edu.study.service;

import edu.study.vo.UserVO;

public interface UserService {

	UserVO login(UserVO vo);
	int selectById(String id);
	int join(UserVO vo);
}
