package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.test.ServiceDaoTestConfig;


public class BoardDaoTest extends ServiceDaoTestConfig{
	
	@Resource(name="boardDao")
	private BoardDao boardDao;
	
	@Test
	public void boardListTest() {
		/***Given***/
		
		
		/***When***/
		List<BoardVo> boardList = boardDao.boardList();

		/***Then***/
		assertEquals(14, boardList.size());
	}
}
