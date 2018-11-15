package kr.or.ddit.post.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PostVo {
	
	private int rnum;
	private int post_no;
	private String post_title;
	private String post_context;
	private String post_rmv;
	
	private int post_board;
	private int post_pid;
	private String userId;
	private String post_user;
	private String post;
	
	private Date formattedDate;

	public String getFormattedDate() {
		if(post_date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(post_date);			
		}else {
			return "";
		}
	}

	public void setFormattedDate(Date formattedDate) {
		this.formattedDate = formattedDate;
	}
	//----------------------------------------
	//생일 :  년 월 일 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date post_date;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPost_user() {
		return post_user;
	}
	public void setPost_user(String post_user) {
		this.post_user = post_user;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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

	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "PostVo [rnum=" + rnum + ", post_no=" + post_no + ", post_title=" + post_title + ", post_context="
				+ post_context + ", post_rmv=" + post_rmv + ", post_board=" + post_board + ", post_pid=" + post_pid
				+ ", userId=" + userId + ", post_user=" + post_user + ", post=" + post + ", formattedDate="
				+ formattedDate + ", post_date=" + post_date + "]";
	}
	
	
	
}
