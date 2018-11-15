package kr.or.ddit.commentary.service;

import java.util.List;

import kr.or.ddit.commentary.model.CommentaryVo;

public interface CommentaryServiceInf {
	
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
	
	
	/**
	 * Method : deleteComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param commentaryVo
	 * @return
	 * Method 설명 : 댓글 삭제 
	 */
	public int deleteComment(int comm_post);
}
