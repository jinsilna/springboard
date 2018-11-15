package kr.or.ddit.attach.model;

public class AttachVo {
	
	private int attach_no;
	private String attach_name;
	private int attach_post;
	
	@Override
	public String toString() {
		return "AttachVo [attach_no=" + attach_no + ", attach_name=" + attach_name + ", attach_post=" + attach_post
				+ "]";
	}
	public AttachVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAttach_no() {
		return attach_no;
	}
	public void setAttach_no(int attach_no) {
		this.attach_no = attach_no;
	}
	public String getAttach_name() {
		return attach_name;
	}
	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}
	public int getAttach_post() {
		return attach_post;
	}
	public void setAttach_post(int attach_post) {
		this.attach_post = attach_post;
	}
	
}
