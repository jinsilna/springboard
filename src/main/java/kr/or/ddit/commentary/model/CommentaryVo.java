package kr.or.ddit.commentary.model;

import java.util.Date;

public class CommentaryVo {
		private int comm_no;
		private String comm_context;
		private Date comm_date;
		private String comm_rmv;
		private int comm_post;
		private String comm_user;
		
		public int getComm_no() {
			return comm_no;
		}
		public void setComm_no(int comm_no) {
			this.comm_no = comm_no;
		}
		public String getComm_context() {
			return comm_context;
		}
		public void setComm_context(String comm_context) {
			this.comm_context = comm_context;
		}
		public Date getComm_date() {
			return comm_date;
		}
		public void setComm_date(Date comm_date) {
			this.comm_date = comm_date;
		}
		public String getComm_rmv() {
			return comm_rmv;
		}
		public void setComm_rmv(String comm_rmv) {
			this.comm_rmv = comm_rmv;
		}
		public int getComm_post() {
			return comm_post;
		}
		public void setComm_post(int comm_post) {
			this.comm_post = comm_post;
		}
		public String getComm_user() {
			return comm_user;
		}
		public void setComm_user(String comm_user) {
			this.comm_user = comm_user;
		}
		
}
