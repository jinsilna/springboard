package kr.or.ddit.board.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.test.ControllerTestConfig;

public class BoardControllerTest extends ControllerTestConfig{

	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	//private Logger logger = LoggerFactory.getLogger(BoardControllerTest.class);
	
	/**
	 * Method : boardListTest
	 * 작성자 : pc07
	 * 변경이력 :
	 * Method 설명 : 게시판 전체 가져오는거 테스트 
	 * @throws Exception 
	 */
	@Test
	public void boardListTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/board/List")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
 
		/***Then***/
		assertEquals("board",viewName);
	}
	
	/**
	 * Method : boardCreateTest
	 * 작성자 : pc07
	 * 변경이력 :
	 * Method 설명 : 게시판 생성하는 테스트 
	 * @throws Exception 
	 */
	/*@Test
	public void boardCreateUpdateTest() throws Exception {
		*//***Given***//*
		MvcResult mvcResult = mockMvc.perform(post("/board/CreateUpdate")).andReturn();

		*//***When***//*
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		*//***Then***//*
		assertEquals("board", viewName);
	}*/
}

