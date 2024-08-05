package edu.study.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.study.service.BoardService;
import edu.study.vo.BoardVO;
import edu.study.vo.SearchVO;
import edu.study.vo.UserVO;

@RequestMapping(value="/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/list.do")
	public String list(Model model,SearchVO searchVO) {
		//DB list 조회
		
		List<BoardVO> list = boardService.list(searchVO);
		
		model.addAttribute("datalist",list);
		
		
		/*
		 * List<BoardVO> list = new ArrayList<BoardVO>();
		 * 
		 * BoardVO vo1 = new BoardVO(); vo1.setTitle("첫번째 게시글"); vo1.setWriter("작성자1");
		 * vo1.setContent("첫번째 내용");
		 * 
		 * list.add(vo1);
		 * 
		 * BoardVO vo2 = new BoardVO(); vo2.setTitle("두번째 게시글"); vo2.setWriter("작성자2");
		 * vo2.setContent("두번째 내용");
		 * 
		 * list.add(vo2);
		 * 
		 * BoardVO vo3 = new BoardVO(); vo3.setTitle("세번째 게시글"); vo3.setWriter("작성자3");
		 * vo3.setContent("세번째 내용");
		 * 
		 * list.add(vo3);
		 * 
		 * model.addAttribute("datalist",list);
		 */
		
		
		
		
		return "board/l";
	}
	
	@RequestMapping(value="/view.do",method=RequestMethod.GET)
	public String view(int bidx, Model model) {
		//DB 상세데이터 조회
		BoardVO vo = boardService.selectByBidx(bidx);
		
		model.addAttribute("vo",vo);
		
		return "board/v";
	}
	
	@RequestMapping(value="/write.do",method=RequestMethod.GET)
	public String write() {
		
		return "board/w";
	}
	
	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(BoardVO vo, HttpSession session) {
		
		UserVO login = (UserVO)session.getAttribute("login");
		
		vo.setUidx(login.getUidx());
		
		
		int result = boardService.insert(vo);
		System.out.println("result : " + result);

		//insert된 게시글의 pk 값을 가져와 view.do로 이동하세요
		
		//int maxBidx = boardService.maxBidx();
		
		//db작업 (insert)
		//return "redirect:list.do"; //redirect되는 가상경로 -> projectpath/board/list.do
		
		return "redirect:view.do?bidx="+vo.getBidx();
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modify(int bidx,Model model) {
		
		BoardVO vo = boardService.selectByBidx(bidx);
		
		model.addAttribute("vo", vo);
		
		return "board/m";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(BoardVO vo) {
		
		int result = boardService.updateByBidx(vo);
		
		if(result>0) {
			return "redirect:view.do?bidx="+vo.getBidx();
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String delete(int bidx,HttpSession session) {
		
		UserVO login =(UserVO)session.getAttribute("login");
		
		int uidx = login.getUidx();
		System.out.println("result : " + uidx);
		
		boardService.deleteByBidx(bidx);
		
		return "redirect:list.do";
	}
}