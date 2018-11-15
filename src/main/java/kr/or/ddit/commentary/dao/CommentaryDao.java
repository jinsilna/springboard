package kr.or.ddit.commentary.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.commentary.model.CommentaryVo;

@Repository
public class CommentaryDao implements CommentaryDaoInf{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
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
		return sqlSessionTemplate.insert("commentary.insertComment",commentaryVo);
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
		return sqlSessionTemplate.update("commentary.deleteComment",comm_post);
	}
	/**
	 * Method : selectPostComment
	 * 작성자 : pc07
	 * 변경이력 :[]]
	 * @param postNo
	 * @return
	 * Method 설명 :  각 게시글에 대한 댓글 조회
	 */
	@Override
	public List<CommentaryVo> selectPostComment(int post_no) {
		return sqlSessionTemplate.selectList("commentary.selectPostComment",post_no);
	}


}
