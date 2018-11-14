package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.test.ServiceDaoTestConfig;

public class BoardServiceTest extends ServiceDaoTestConfig{
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Test
	public void boardListServiceTest() {
		/***Given***/
		

		/***When***/
		List<BoardVo> boardList = boardService.boardList();
		
		/***Then***/
		assertEquals(18, boardList.size());
	}
	@Test
	public void boardUserListTest() {
		
		/***Given***/
		

		/***When***/
		List<BoardVo> boarduserList = boardService.boardUserList();
		/***Then***/
		assertEquals(4, boarduserList.size());
	}
	
	@Test
	public void insertBoardListTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoard_no("24");
		boardVo.setBoard_name("생성게시판");
		boardVo.setBoard_insdate("18/11/13");
		boardVo.setBoard_use("N");
		boardVo.setBoard_user("cony");
		/***When***/
		
		int insertBoard = boardService.insertBoardList(boardVo);
		/***Then***/
		assertEquals(1, insertBoard);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_no("24");
		boardVo.setBoard_name("생성게시판");
		boardVo.setBoard_insdate("18/11/13");
		boardVo.setBoard_use("N");
		boardVo.setBoard_user("cony");
		/***When***/
		
		int updateBoard = boardService.updateBoard(boardVo);
		/***Then***/
		assertEquals(0, updateBoard);
	}
}
