package cn.itcast.QLN.user.service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import cn.itcast.QLN.document.dao.DocumentDao;
import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.dao.UserDao;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBean;

@Transactional
public class UserService {
//注入Dao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
    //业务层完成用户注册，数据存入到数据库
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		
	}
//用户登录
	public User login(User user,String selectJobtitle) {
		// TODO Auto-generated method stub
		return userDao.login(user,selectJobtitle);
	}

}
