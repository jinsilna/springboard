package kr.or.ddit.attach.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attach.model.AttachVo;

@Repository
public class AttachDao implements AttachDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	 * Method : insertFile
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param attchVo
	 * @return
	 * Method 설명 : 파일 생성
	 */
	@Override
	public int insertFile(AttachVo attchVo) {
		return sqlSessionTemplate.insert("attach.insertFile",attchVo);
	}

	/**
	 * Method : updateFile
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param attachVo
	 * @return
	 * Method 설명 : 파일 수정
	 */
	@Override
	public int updateFile(AttachVo attachVo) {
		return sqlSessionTemplate.update("attach.updateFile",attachVo);
	}

	/**
	 * Method : selectAttachment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param post_no
	 * @return
	 * Method 설명 : 게시글에 맞는 파일 
	 */
	@Override
	public List<AttachVo> selectAttachment(int post_no) {
		return sqlSessionTemplate.selectList("attach.selectAttachment",post_no);
	}

}
