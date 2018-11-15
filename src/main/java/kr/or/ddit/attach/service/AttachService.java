package kr.or.ddit.attach.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attach.dao.AttachDaoInf;
import kr.or.ddit.attach.model.AttachVo;

@Service
public class AttachService implements AttachServiceInf{

	@Resource(name="attachDao")
	private AttachDaoInf attachDao;
	
	@Override
	public int insertFile(AttachVo attchVo) {
		return attachDao.insertFile(attchVo);
	}

	@Override
	public int updateFile(AttachVo attachVo) {
		return attachDao.updateFile(attachVo);
	}

	@Override
	public List<AttachVo> selectAttachment(int post_no) {
		return attachDao.selectAttachment(post_no);
	}
}
