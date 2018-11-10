package kr.or.ddit.board.model;

public class BoardVo {
	
	private String board_no;
	private String board_name;
	private String board_use;
	private String board_user;
	private String board_insdate;
	
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_use() {
		return board_use;
	}
	public void setBoard_use(String board_use) {
		this.board_use = board_use;
	}
	public String getBoard_user() {
		return board_user;
	}
	public void setBoard_user(String board_user) {
		this.board_user = board_user;
	}
	public String getBoard_insdate() {
		return board_insdate;
	}
	public void setBoard_insdate(String board_insdate) {
		this.board_insdate = board_insdate;
	}
	
	public void setBoardUsing(String board_user){
		if(board_user.equals("사용")){
			setBoard_use("Y");
		}else{
			setBoard_use("N");
		}
	}
	@Override
	public String toString() {
		return "BoardVo [board_no=" + board_no + ", board_name=" + board_name + ", board_use=" + board_use
				+ ", board_user=" + board_user + ", board_insdate=" + board_insdate + "]";
	}
}
