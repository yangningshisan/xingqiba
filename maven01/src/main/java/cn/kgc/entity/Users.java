package cn.kgc.entity;

import java.util.Date;

public class Users {
	private Integer user_id;
	private String user_code;
	private String password;
	private String email;
	private String gender;
	private Date register_time;
	private Date last_logintime;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer userId) {
		user_id = userId;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date registerTime) {
		register_time = registerTime;
	}
	public Date getLast_logintime() {
		return last_logintime;
	}
	public void setLast_logintime(Date lastLogintime) {
		last_logintime = lastLogintime;
	}
	public Users(Integer userId, String userCode, String password,
			String email, String gender, Date registerTime, Date lastLogintime) {
		super();
		user_id = userId;
		user_code = userCode;
		this.password = password;
		this.email = email;
		this.gender = gender;
		register_time = registerTime;
		last_logintime = lastLogintime;
	}
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [email=" + email + ", gender=" + gender
				+ ", last_logintime=" + last_logintime + ", password="
				+ password + ", register_time=" + register_time
				+ ", user_code=" + user_code + ", user_id=" + user_id + "]";
	}
	
	

}
