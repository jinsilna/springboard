package kr.or.ddit.post.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

@Repository
public class PostDao implements PostDaoInf{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public int insertPostNo(PostVo postVo) {
		return sqlSessionTemplate.insert("post.insertPostNo",postVo);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return sqlSessionTemplate.insert("post.insertPost",postVo);
	}

	@Override
	public int updatePost(PostVo postVo) {
		return sqlSessionTemplate.update("post.updatePost",postVo);
	}

	@Override
	public int deletePost(int postNo) {
		return sqlSessionTemplate.delete("post.deletePost",postNo);
	}

	@Override
	public PostVo selectOnePost(int post_no) {
		return sqlSessionTemplate.selectOne("post.selectOnePost",post_no);
	}

	@Override
	public List<PostVo> postBoardList(PageVo pageVo) {
		return sqlSessionTemplate.selectList("post.selectPostBoardList",pageVo);
	}

	@Override
	public int postBoardCnt(int boardNo) {
		return sqlSessionTemplate.selectOne("post.PostBoardCnt", boardNo);
	}

}
