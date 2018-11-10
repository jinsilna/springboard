package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface BoardServiceInf {
	
	/**
	 * Method : boardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 리스트 
	 */
	public List<BoardVo> boardList();
	
	
	/**
	 * Method : boardUserList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 사용여부가 Y 인 전체정보 출력
	 */
	public List<BoardVo> boardUserList();
	
	/**
	 * Method : insertBoardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 등록 
	 */
	public int insertBoardList(BoardVo boardVo);
	
	/**
	 * Method : updateBoard
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	public int updateBoard(BoardVo boardVo);
}
