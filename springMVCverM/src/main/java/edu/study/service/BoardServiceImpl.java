package edu.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.BoardDAO;
import edu.study.vo.BoardVO;
import edu.study.vo.SearchVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> list(SearchVO searchVO) {
		// TODO Auto-generated method stub
		List<BoardVO> list = boardDAO.list(searchVO);
		
		return list;
	}

	@Override
	public BoardVO selectByBidx(int bidx) {
		
		return boardDAO.selectByBidx(bidx);
	}

	@Override
	public int updateByBidx(BoardVO vo) {
		return boardDAO.updateByBidx(vo);
	}

	@Override
	public int deleteByBidx(int bidx) {
//		boardDAO.deleteFromReply(bidx);
		return boardDAO.deleteByBidx(bidx);
	}

	@Override
	public int insert(BoardVO vo) {
		return boardDAO.insert(vo);
	}

	/*
	 * @Override public int maxBidx() { return boardDAO.maxBidx(); }
	 */
}
