package cn.itcast.QLN.user.dao;

import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.QLN.user.vo.User;

public class UserDao extends HibernateDaoSupport {


public User findByUsername(String username) {
		// TODO Auto-generated method stub
				String hql="from User where username=?";
				List<User> list=this.getHibernateTemplate().find(hql, username);
				if(list!=null&&list.size()>0){
					return list.get(0);//如果查寻到用户存在，则取第一个用户
				}
				return null;
	}

public void save(User user) {
	// TODO Auto-generated method stub
	//直接调用getHibernateTemplate中的sava方法即可将数据存入数据库
	this.getHibernateTemplate().save(user);
	
}

public User login(User user, String selectJobtitle) {
	// TODO Auto-generated method stub
	  String hql="from User u where u.username=? and u.password=? and u.jobtitle=?";
      List<User> list= this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),selectJobtitle);
      if(list!=null&&list.size()>0){
  		return list.get(0);//如果查寻到用户存在，则取第一个用户
  	}
  	return null;
}


}
