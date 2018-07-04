package cn.kgc.service;

import cn.kgc.dao.UserDAO;
import cn.kgc.entity.Users;

public class UserService {
	
	private UserDAO userDAO = new UserDAO();
	
	/**
	 * µÇÂ¼¹¦ÄÜ
	 * @param userCode
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Users login(String userCode,String password) throws Exception{
		Users user = userDAO.findByCode(userCode);
		if(user==null){
			throw new Exception("ÕËºÅ²»´æÔÚ");
		}else{
			if(!user.getPassword().equals(password)){
				throw new Exception("ÃÜÂë´íÎó");
			}
		}
		return user;
	}
	
	/**
	 * ×¢²á
	 * @param user
	 * @return
	 */
	public int register(Users user){
		return userDAO.addUser(user);
	}
	
	
	
}
