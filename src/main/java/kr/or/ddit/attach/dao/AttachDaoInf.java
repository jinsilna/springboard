package kr.or.ddit.attach.dao;

import java.util.List;

import kr.or.ddit.attach.model.AttachVo;

public interface AttachDaoInf {

	/**
	 * Method : insertFile
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param attchVo
	 * @return
	 * Method 설명 : 파일 생성
	 */
	public int insertFile(AttachVo attchVo);
	
	/**
	 * Method : updateFile
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param attachVo
	 * @return
	 * Method 설명 : 파일 수정
	 */ 
	public int updateFile(AttachVo attachVo);
	
	/**
	 * Method : selectAttachment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param post_no
	 * @return
	 * Method 설명 : 게시글에 맞는 첨부파일 가져오기 
	 */
	public List<AttachVo> selectAttachment(int post_no);	
}
