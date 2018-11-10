package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.format.annotation.DateTimeFormat;

public class UserVo implements HttpSessionBindingListener{
	private String userId;
	private String name;
	private String pass;
	private String add1;
	private String add2;
	private String zip;
	private String email;
	private String tel;
	private String profile;
	private String alias;
	private int    rnum;
	
	//----------------------------------------
	//생일 :  년 월 일 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	//----------------------------------------
	

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	// 기본생성자를 명시적으로 생성하는것이 나중에 에러 방지에 좋다. 
	public UserVo(){	
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getBirthRomat(Date birth){
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = fomat.format(birth);
		return toDate;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", name=" + name + ", pass=" + pass + ", add1=" + add1 + ", add2=" + add2
				+ ", zip=" + zip + ", email=" + email + ", tel=" + tel + ", profile=" + profile + ", alias=" + alias
				+ ", rnum=" + rnum + ", birth=" + birth + "]";
	}

	/**
	 * Method : authPass
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param encryptPass
	 * @return
	 * Method 설명 : 비밀번호 검증
	 */
	public boolean authPass(String encryptPass) {
		return getPass().equals(encryptPass);
	}

	// 
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
	    System.out.println( "userVo : "+ event.getName());	
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		
	}
}


