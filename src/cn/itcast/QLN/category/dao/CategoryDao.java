package cn.itcast.QLN.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.document.vo.Document;

public class CategoryDao extends HibernateDaoSupport {

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}


}
