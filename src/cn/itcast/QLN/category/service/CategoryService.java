package cn.itcast.QLN.category.service;

import java.util.List;

import cn.itcast.QLN.category.dao.CategoryDao;
import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.document.vo.Document;

public class CategoryService {
 private CategoryDao categoryDao;

public void setCategoryDao(CategoryDao categoryDao) {
	this.categoryDao = categoryDao;
}

public List<Category> findAll() {
	// TODO Auto-generated method stub
	return categoryDao.findAll();
}



}
