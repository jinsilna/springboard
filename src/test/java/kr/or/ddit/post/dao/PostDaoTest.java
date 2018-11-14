package kr.or.ddit.post.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.test.ServiceDaoTestConfig;

public class PostDaoTest extends ServiceDaoTestConfig{

	
	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	@Test
	public void insertPostNoTest() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setPost_no(130);
		postVo.setPost_pid(22);
		postVo.setPost_board(2);
		postVo.setPost_context("글 추가중");
		postVo.setPost_rmv("Y");
		postVo.setPost_title("글제목");
		postVo.setUserId("brown");
		

		/***When***/
		int insertPost = postDao.insertPostNo(postVo);


		/***Then***/
		assertEquals(1, insertPost);
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setPost_no(130);
		postVo.setPost_pid(22);
		postVo.setPost_board(2);
		postVo.setPost_context("글 추가중");
		postVo.setPost_rmv("Y");
		postVo.setPost_title("글제목");
		postVo.setUserId("brown");
		

		/***When***/
		int insertPost = postDao.insertPost(postVo);


		/***Then***/
		assertEquals(1, insertPost);
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setPost_no(130);
		postVo.setPost_pid(22);
		postVo.setPost_board(2);
		postVo.setPost_context("글 추가중");
		postVo.setPost_rmv("Y");
		postVo.setPost_title("글제목");
		postVo.setUserId("brown");
		
		/***When***/
		int updatePost = postDao.updatePost(postVo);
		
		/***Then***/
		assertEquals(0, updatePost);
	}
	
	
	@Test
	public void deletePostTest() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setPost_no(130);
		postVo.setPost_pid(22);
		postVo.setPost_board(2);
		postVo.setPost_context("글 추가중");
		postVo.setPost_rmv("Y");
		postVo.setPost_title("글제목");
		postVo.setUserId("brown");
		
		/***When***/
		int deleteboard = postDao.deletePost(postVo.getPost_no());
		
		/***Then***/
		assertEquals(0, deleteboard);
	}
	
	@Test
	public void selectOnePostTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
	}
	
	@Test
	public void postBoardListTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
	}
	
	@Test
	public void postBoardCntTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
	}
}
