package cn.kgc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.util.DBHelper;

/**
 * ͨ��baseDAO������,���,��ɾ�ģ�
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class BaseDAO<T> {
	public Connection con;
	public ResultSet rs;
	public PreparedStatement pstm;
	
	/**
	 * ͨ�ò�ѯ����������
	 * @param sql
	 * @param objs
	 * @param clazz
	 * @return
	 */
	public List<T> queryList(String sql,Object[] objs,Class<T> clazz){
		if(con==null){
			con = DBHelper.getCon();
		}
		List<T> list = new ArrayList<T>();
		try {
			pstm = con.prepareStatement(sql);
			//���ò������±��1��ʼ
			for (int i = 0; i < objs.length; i++) {
				pstm.setObject(i+1, objs[i]);
			}
			rs = pstm.executeQuery();
			//��ȡ����Ϣ
			ResultSetMetaData metaData = rs.getMetaData();
			//��ȡ����
			int columns = metaData.getColumnCount();
			while (rs.next()) {
				//���÷������ʵ�����set����
				//new
				Object obj = clazz.newInstance();
				for (int i = 1; i <= columns; i++) {
					//�����������������
					String columnName = metaData.getColumnName(i);
					//��ȡ������Ϣ
					Field field = clazz.getDeclaredField(columnName);
					//����Ҫ���õķ������Ͳ�������
					Method method = clazz.getDeclaredMethod("set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1),field.getType());
					//���÷���
					method.invoke(obj, rs.getObject(i));
				}
				list.add((T)obj);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return list;
	}
	
	/**
	 * ͨ����ɾ��
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql,Object[] obj){
		if(con==null){
			con=DBHelper.getCon();
		}
		int result = 0;
		try {
			pstm = con.prepareStatement(sql);
			//���ò���
			for (int i = 0; i < obj.length; i++) {
				pstm.setObject(i+1, obj[i]);
			}
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return result;
	}
	
	/**
	 * ����ѯ ����list<map>
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List<Map<String, Object>> queryListMap(String sql,Object[] objs){
		if(con==null){
			con = DBHelper.getCon();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			pstm = con.prepareStatement(sql);
			//���ò���
			for (int i = 0; i < objs.length; i++) {
				pstm.setObject(i+1, objs[i]);
			}
			rs = pstm.executeQuery();
			//��ȡ����Ϣ
			ResultSetMetaData metaData = rs.getMetaData();
			//��ȡ�е�����
			int colmns = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= colmns; i++) {
					//��ȡ����
					String columnName = metaData.getColumnName(i);
					map.put(columnName, rs.getObject(columnName));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBHelper.closeAll(rs, pstm, null);
		}
		return list;
		
	}
	
	
	
	
	
	
}
