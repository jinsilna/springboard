package kr.or.ddit.commentary.dao;

import java.util.List;

import kr.or.ddit.commentary.model.CommentaryVo;

public interface CommentaryDaoInf {
	
	
	/**
	 * Method : insertComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param commentaryVo
	 * @return
	 * Method 설명 : 각 게시글에 해당한 댓글 작성
	 */
	public int insertComment(CommentaryVo commentaryVo);
	
	
	
	/**
	 * Method : selectPostComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postNo
	 * @return
	 * Method 설명 :  각 게시글에 대한 댓글 조회
	 */
	public List<CommentaryVo> selectPostComment(int post_no);
}
