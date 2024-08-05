package edu.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.UserDAO;
import edu.study.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO login(UserVO vo) {
		return userDAO.login(vo);
	}

	@Override
	public int selectById(String id) {
		return userDAO.selectById(id);
	}
	
	@Override
	public int join(UserVO vo) {
	
		return userDAO.join(vo);
	}

}
