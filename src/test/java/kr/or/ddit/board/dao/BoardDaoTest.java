package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.test.ServiceDaoTestConfig;


public class BoardDaoTest extends ServiceDaoTestConfig{
	
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Test
	public void boardListTest() {
		/***Given***/
		
		
		/***When***/
		List<BoardVo> boardList = boardDao.boardList();

		/***Then***/
		assertEquals(14, boardList.size());
	}
	
	@Test
	public void boardUserListTest() {
		
		/***Given***/
		

		/***When***/
		List<BoardVo> boarduserList = boardDao.boardUserList();
		/***Then***/
		assertEquals(4, boarduserList.size());
	}
	
	@Test
	public void insertBoardListTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoard_no("24");
		boardVo.setBoard_name("게시게시판");
		boardVo.setBoard_insdate("18/11/13");
		boardVo.setBoard_use("N");
		boardVo.setBoard_user("sally");
		/***When***/
		
		int insertBoard = boardDao.insertBoardList(boardVo);
		/***Then***/
		assertEquals(1, insertBoard);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoard_no("24");
		boardVo.setBoard_name("게시게시판");
		boardVo.setBoard_insdate("18/11/13");
		boardVo.setBoard_use("N");
		boardVo.setBoard_user("sally");
		/***When***/
		
		int updateBoard = boardDao.updateBoard(boardVo);
		/***Then***/
		assertEquals(0, updateBoard);
	}
}
