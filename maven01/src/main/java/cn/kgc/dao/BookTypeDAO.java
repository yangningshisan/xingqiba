package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.BookType;

public class BookTypeDAO extends BaseDAO<BookType> {
	/**
	 * 查询所有图书分类
	 * @return
	 */
	public List<BookType> findAllType(){
		String sql = "select * from book_type";
		return super.queryList(sql, new Object[]{}, BookType.class);
	}
}
