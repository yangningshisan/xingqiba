package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Users;

public class UserDAO extends BaseDAO<Users> {
	
	/**
	 * 通过账号查询单个
	 * @param userCode
	 * @return
	 */
	public Users findByCode(String userCode){
		String sql = "select * from users where user_code = ?";
		List<Users> users = super.queryList(sql, new Object[]{userCode}, Users.class);
		if(users==null||users.size()==0){
			return null; 
		}else{
			return users.get(0);
		}
	}
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(Users user){
		String sql = "insert into users(user_code,password,gender,email) values (?,?,?,?)";
		return super.update(sql, new Object[]{user.getUser_code(),user.getPassword(),user.getGender(),user.getEmail()});
	}
	
	
	
	
	
}
