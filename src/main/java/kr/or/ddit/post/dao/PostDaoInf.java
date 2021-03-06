package kr.or.ddit.post.dao;

import java.util.List;

import kr.or.ddit.util.model.PageVo;
import kr.or.ddit.post.model.PostVo;

public interface PostDaoInf {
	
	/**
	 * Method : insertPostNo
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 
	 */
	public int insertPostNo(PostVo postVo);
	
	/**
	 * Method : insertPost
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 추가 
	 */
	public int insertPost(PostVo postVo);
	
	/**
	 * Method : updatePost
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	public int updatePost(PostVo postVo);
	
	/**
	 * Method : deletePost
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param postNo
	 * @return
	 * Method 설명 : 게시글 삭제
	 */
	public int deletePost(int post_no);
	
	/**
	 * Method : selectOnePost
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param post_no
	 * @return
	 * Method 설명 : 게시글 번호로 하나의 게시글 가져오기 
	 */
	public PostVo selectOnePost(int post_no);
	
	/**
	 * Method : postBoardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 게시글 목록 가져오기 (페이징처리)
	 */
	public List<PostVo> postBoardList(PageVo pageVo);
	
	/**
	 * Method : postBoardCnt
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param boardNo
	 * @return
	 * Method 설명 : 게시글 갯수 조회 
	 */
	public int postBoardCnt(int boardNo);
}
