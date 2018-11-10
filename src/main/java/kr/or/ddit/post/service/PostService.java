package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.dao.PostDaoInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

@Service
public class PostService implements PostServiceInf{

	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	@Override
	public int insertPostNo(PostVo postVo) {
		return postDao.insertPostNo(postVo);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}

	@Override
	public int updatePost(PostVo postVo) {
		return postDao.updatePost(postVo);
	}

	@Override
	public int deletePost(int postNo) {
		return postDao.deletePost(postNo);
	}

	@Override
	public PostVo selectOnePost(int post_no) {
		return postDao.selectOnePost(post_no);
	}

	@Override
	public Map<String, Object> postBoardList(PageVo pageVo) {
		List<PostVo> postList = postDao.postBoardList(pageVo);
		int totalBoardCnt = postDao.postBoardCnt(pageVo.getBoard_no());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postList", postList);
		resultMap.put("pageCnt", (int)Math.ceil((double)totalBoardCnt/pageVo.getPageSize()));
		
		return resultMap;
	}
}
