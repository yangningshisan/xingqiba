package cn.kgc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBHelper {
	static String driver="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://127.0.0.1:3306/yangning";
	static String username="root";
	static String password="1234";
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ�����
	public static Connection getCon(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//�ر�
	public static void closeAll(ResultSet rs,PreparedStatement pstm,Connection con){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
