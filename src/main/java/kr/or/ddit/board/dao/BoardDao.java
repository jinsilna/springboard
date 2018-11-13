package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;

@Repository
public class BoardDao implements BoardDaoInf{
		
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
		
	/**
	 * Method : boardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 가져오기 
	 */
	@Override
	public List<BoardVo> boardList() {
		return sqlSessionTemplate.selectList("board.selectBoard");
	}

	/**
	 * 
	 * Method : boardUserList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 사용여부가 'Y'만 가져오기 
	 */
	@Override
	public List<BoardVo> boardUserList() {
		return sqlSessionTemplate.selectList("board.selectUseBoard");
	}

	/**
	 * Method : insertBoardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 생성하기 
	 */
	@Override
	public int insertBoardList(BoardVo boardVo) {
		return sqlSessionTemplate.insert("board.insertBoard",boardVo) ;
	}

	/**
	 * Method : updateBoard
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 수정하기 
	 */
	@Override
	public int updateBoard(BoardVo boardVo) {		
		return sqlSessionTemplate.update("board.updateBoard", boardVo);
	}

}
