package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;

@Service
public class BoardService implements BoardServiceInf{

	@Resource(name="boardDao")
	private BoardDaoInf boardDao;

	@Override
	public List<BoardVo> boardList() {
		return boardDao.boardList();
	}

	@Override
	public List<BoardVo> boardUserList() {
		return boardDao.boardUserList();
	}

	@Override
	public int insertBoardList(BoardVo boardVo) {
		return boardDao.insertBoardList(boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return boardDao.updateBoard(boardVo);
	}
	
}
