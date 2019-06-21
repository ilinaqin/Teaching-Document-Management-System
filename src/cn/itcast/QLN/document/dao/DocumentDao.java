package cn.itcast.QLN.document.dao;

import java.util.List;

//import javax.mail.Session;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.SessionFactoryObjectFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBeanUtil;
import cn.itcast.QLN.utils.PageHibernateCallback;


public class DocumentDao extends HibernateDaoSupport{
	
	// 后台查询所有商品的方法
	public List<Document> findByPage(Integer uid, int begin, int limit) {
		String hql = "select d from Document d where d.user.uid = ? order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql,  new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}
	
	// 后台统计商品个数的方法
	public int findCount(Integer uid) {
		String hql = "select count(*) from Document d where d.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public int findCountCid(Integer cid,int uid) {
		String hql = "select count(*) from Document d where d.category.cid = ? and d.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid, uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public List<Document> findByCid(Integer cid, int begin, int limit,int uid) {
		String hql = "select d from Document d join d.category c where c.cid = ? and d.user.uid = ? order by d.dtime desc";
		List<Document> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{cid,uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	
	public void save(Document document) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(document);
	}


	public int findKeyCount(String docsearch,int uid) {
			// TODO Auto-generated method stub
             String hql= "select count(*) from Document d where d.ddescrip like ? and d.user.uid=?";
		    List<Long> list = this.getHibernateTemplate().find(hql,"%"+docsearch+"%",uid);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findByKey(String docsearch, int begin, int limit,int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.ddescrip LIKE ? and d.user.uid=? order by d.dtime desc";
		
		List<Document> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{"%"+docsearch+"%",uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public Document findByDid(Integer did) {
		// TODO Auto-generated method stub
	
		String hql = "select d from Document d  where d.did = ?";
		List<Document> list = this.getHibernateTemplate().find(hql,did);
			return list.get(0);

	}

	public void delete(Document document) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(document);
	}


	public Document checkState(Integer did) {
		// TODO Auto-generated method stub
		String hql = "from Document where did=?";
		List<Document> list = this.getHibernateTemplate().find(hql,did);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
		
	}

	public void update(Document existDocument) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(existDocument);
	}

	public int findManagerCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state=1 or d.state=-1 or d.state=2 or d.state=3 or d.state=-2";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findByManagerPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.state=1 or d.state=-1 or d.state=2 or d.state=3 or d.state=-2 order by teachersubtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findManagerUncheckCount() {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.state=1 or d.state=-2";
				List<Long> list = this.getHibernateTemplate().find(hql);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findManagerUncheck(int begin, int limit) {
		
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.state=1 or d.state=-2 order by teachersubtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findManagerCheckedCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state=2 or d.state=3";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findManagerChecked(int begin, int limit) {

		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.state=2 or d.state=3 order by dtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findUnsubmitCount(int uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.user.uid = ? and (d.state = 0 or d.state = -1)";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findUnsubmit(int begin, int limit,int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and (d.state = 0 or d.state = -1) order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findUnpassCount(int uid) {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where  d.user.uid = ? and  d.state = -1";
				List<Long> list = this.getHibernateTemplate().find(hql,uid);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findUnpass(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and d.state = -1 order by mantime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findSubmitedCount(int uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.user.uid = ? and ( d.state = 1 or d.state = 2 or d.state = 3 )";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findSubmited(int begin, int limit,int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and ( d.state = 1 or d.state = 2 or d.state = 3 ) order by teachersubtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findCheckpass1Count(int uid) {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.user.uid = ? and d.state = 2";
				List<Long> list = this.getHibernateTemplate().find(hql,uid);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findCheckpass1(int begin, int limit,int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and d.state = 2 order by mantime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	
	public int findCheckpass2Count(int uid) {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.user.uid = ? and d.state = 3";
				List<Long> list = this.getHibernateTemplate().find(hql,uid);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findCheckpass2(int begin, int limit,int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and d.state = 3 order by leadtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findManagerUnpassCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state = -2";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findManagerUnpass(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.state = -2 order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findByLeaderPageCount() {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.state = 2 or d.state = 3";
				List<Long> list = this.getHibernateTemplate().find(hql);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findByLeaderPage(int begin, int limit) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.state = 2 or d.state = 3 order by dtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findLeaderCheckedCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state = 3";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findLeaderChecked(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where  d.state = 3 order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findLeaderUncheckCount() {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.state = 2";
				List<Long> list = this.getHibernateTemplate().find(hql);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findLeaderUncheck(int begin, int limit) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where  d.state = 2 order by dtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public int findBySecPageCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state = 3";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
}
	public List<Document> findBySecPages(int begin, int limit) {
		String hql = "select d from Document d where d.state = 3 order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findSecUnplacePageCount() {
		// TODO Auto-generated method stub
				String hql = "select count(*) from Document d where d.place ='尚未归档' and d.state = 3";
				List<Long> list = this.getHibernateTemplate().find(hql);
				if(list != null && list.size() > 0){
					return list.get(0).intValue();
				}
				return 0;
	}

	public List<Document> findSecUnplacePage(int begin, int limit) {
		String hql = "select d from Document d where d.place ='尚未归档' and d.state = 3  order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int findSecPlacedPageCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.place <> '尚未归档' and d.state = 3";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findSecPlacedPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.place <> '尚未归档' and d.state = 3 order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;	}

	public int findManCheckpass2Count() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Document d where d.state = 3";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> findManCheckpass2(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.state = 3 order by dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null , begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public Object timeasc() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Document> timeasc(int uid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? order by dtime asc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql,  new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> typeasc(int uid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? order by cid desc, dtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql,  new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> CidTimeasc(Integer cid, int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d join d.category c where c.cid = ? and d.user.uid = ? order by d.dtime asc";
		List<Document> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{cid,uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public List<Document> unsubmitTimeasc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and (d.state = 0 or d.state = -1) order by dtime asc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> unsubmittypedesc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and (d.state = 0 or d.state = -1) order by cid desc, dtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public List<Document> manTimeasc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and d.state = -1 order by mantime asc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> mantypedesc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and d.state = -1 order by cid desc, mantime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql,  new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> submitedteacherTimeasc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and ( d.state = 1 or d.state = 2 or d.state = 3 ) order by teachersubtime asc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public List<Document> submitedtypedesc(int begin, int limit, int uid) {
		
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and ( d.state = 1 or d.state = 2 or d.state = 3 ) order by cid desc, teachersubtime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql,  new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public List<Document> checkpass1Timeasc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and d.state = 2 order by mantime asc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> checkpass2Timeasc(int begin, int limit, int uid) {
		String hql = "select d from Document d where d.user.uid = ? and d.state = 3 order by leadtime asc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> checkpass1typedesc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.user.uid = ? and d.state = 2 order by cid desc , mantime desc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public List<Document> checkpass2typedesc(int begin, int limit, int uid) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.user.uid = ? and d.state = 3 order by cid desc , leadtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public int managercheckunpassCount() {
		String hql = "select count(*) from Document d where  d.state = -1";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Document> managercheckunpass(int begin, int limit) {
		String hql = "select d from Document d where d.state = -1 order by mantime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null , begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public List<Document> managerAllPageTimeasc(int begin, int limit) {
		// TODO Auto-generated method stub
				String hql = "select d from Document d where d.state=1 or d.state=-1 or d.state=2 or d.state=3 or d.state=-2 order by teachersubtime asc";
				List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
				if(list != null && list.size() > 0){
					return list;
					
				}
				return null;
	}

	public List<Document> managerAllPageTypedesc(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select d from Document d where d.state=1 or d.state=-1 or d.state=2 or d.state=3 or d.state=-2 order by cid desc , teachersubtime desc";
		List<Document> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Document>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
			
		}
		return null;
	}

	public void pageQuery(PageBeanUtil<Document> pageBeanUtil) {
		// TODO Auto-generated method stub
		int page=pageBeanUtil.getPage();
		int pageSize=pageBeanUtil.getPageSize();
		
		System.out.println("查询记录数为："+pageSize);
		
		//查询条件
		DetachedCriteria detachedCriteria=pageBeanUtil.getDetachedCriteria();
		//这里需要的是total和rows
		detachedCriteria.setProjection(Projections.rowCount());//强制发出select count（*）的语句
		List list=this.getHibernateTemplate().findByCriteria(detachedCriteria);
		long total=(Long)list.get(0);
		System.out.println("总数为："+total);
		
		detachedCriteria.setProjection(null);//清空之前的count查询规则设置
		//让其查询出实体对象集而不是object集合
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		
		//进行分页查询
		int firstResult=(page-1)*pageSize;
		int maxResults=pageSize;
		List rows=this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResults);
		pageBeanUtil.setTotal(total);//把查询出来的响应数据封装在pageBeanUtil中
		pageBeanUtil.setRows(rows);
	}

}
