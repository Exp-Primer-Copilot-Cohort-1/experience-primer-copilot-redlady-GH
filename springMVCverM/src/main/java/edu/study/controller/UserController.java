package edu.study.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*import org.springframework.web.bind.annotation.ResponseBody;*/

import edu.study.service.UserService;
import edu.study.vo.UserVO;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login() {
		
		
		return "user/login";
	}
	/*
	  로그인버튼 클릭 시 사용자가 입력한 아이디 패스워드 정보와 일치하는 usertb 데이터 controller까지 조회해 오기 
	  
	 */

	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		
		//여기로 데이터 조회해오세요
		UserVO loginVO = userService.login(vo);
		
		if(loginVO != null) {
			session.setAttribute("login", loginVO);

			System.out.println(loginVO.toString());
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	@RequestMapping(value="/join.do",method=RequestMethod.POST)
	public String join(UserVO vo, HttpServletRequest request) {
		int result = userService.join(vo);
		System.out.println("join 결과 : " + result);
		
		return "redirect:/";
	}
}