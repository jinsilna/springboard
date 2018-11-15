package kr.or.ddit.commentary.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.commentary.dao.CommentaryDaoInf;
import kr.or.ddit.commentary.model.CommentaryVo;

@Service
public class CommentaryService implements CommentaryServiceInf{
	
	@Resource(name="commentaryDao")
	private CommentaryDaoInf commentaryDao;
	
	/**
	 * Method : insertComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param commentaryVo
	 * @return
	 * Method 설명 : 각 게시글에 해당한 댓글 작성
	 */
	@Override
	public int insertComment(CommentaryVo commentaryVo) {
		return commentaryDao.insertComment(commentaryVo);
	}

	/**
	 * Method : selectPostComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postNo
	 * @return
	 * Method 설명 :  각 게시글에 대한 댓글 조회
	 */
	@Override
	public List<CommentaryVo> selectPostComment(int post_no) {
		return commentaryDao.selectPostComment(post_no);
		
		
	}

	/**
	 * Method : deleteComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param comm_post
	 * @return
	 * Method 설명 : 댓글 삭제 
	 */
	@Override
	public int deleteComment(int comm_post) {
		return commentaryDao.deleteComment(comm_post);
	}

}
