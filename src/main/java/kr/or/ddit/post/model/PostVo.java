package kr.or.ddit.post.model;

import java.util.Date;

public class PostVo {
	
	private int rnum;
	private int post_no;
	private String post_title;
	private String post_context;
	private String post_rmv;
	private Date post_date;
	private int post_board;
	private int post_pid;
	private String userId;
	
	
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_context() {
		return post_context;
	}
	public void setPost_context(String post_context) {
		this.post_context = post_context;
	}
	public String getPost_rmv() {
		return post_rmv;
	}
	public void setPost_rmv(String post_rmv) {
		this.post_rmv = post_rmv;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public int getPost_board() {
		return post_board;
	}
	public void setPost_board(int post_board) {
		this.post_board = post_board;
	}
	public int getPost_pid() {
		return post_pid;
	}
	public void setPost_pid(int post_pid) {
		this.post_pid = post_pid;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	
	
}
