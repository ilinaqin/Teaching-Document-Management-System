package cn.itcast.QLN.document.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.DataDirectOracle9Dialect;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.document.ObjectJsonValueProcessor;
import cn.itcast.QLN.document.service.DocumentService;
import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBean;
import cn.itcast.QLN.utils.PageBeanUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ManagerDocumentAction extends ActionSupport implements ModelDriven<Document> {
	
	// 模型驱动使用的对象
			private Document document = new Document();
			
			public Document getModel() {
				return document;
			}
	
	private DocumentService documentService;
	
	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	//接收当前页数，跳转的时候方便显示某一页的列表数据
	  private int page;
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 查询显示老师提交的所有文档
	 */
	int rows;
    

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String findManagerPage(){
		 return "findManagerPage";
	}
	
	public String findManagerPage00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
           int uid=existUser.getUid();
           
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).addOrder(Order.desc("teachersubtime"));
		Disjunction dis= Restrictions.disjunction();              
	            dis.add(Restrictions.eq("state", 1)) ;
	            dis.add(Restrictions.eq("state", 2)) ;
	            dis.add(Restrictions.eq("state", 3));
	            dis.add(Restrictions.eq("state", -2)) ;
	            dis.add(Restrictions.eq("state", -1));
	    detachedCriteria.add(dis);
	            
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图

			}
	
	/**
	 * 查询所有需要提交文档
	 * @return
	 */
	public String uncheckManagerDoc(){
			return "uncheckDoc";
			}
	public String uncheckManagerDoc00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
         int uid=existUser.getUid();
         
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).addOrder(Order.desc("teachersubtime"));
		Disjunction dis= Restrictions.disjunction();
	            dis.add(Restrictions.eq("state", 1)) ;
	            dis.add(Restrictions.eq("state", -2));
	    detachedCriteria.add(dis);
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图

		}
	
	
	/**
	 * 查询所有二级审核未通过文档
	 * @return
	 */
	public String unpass(){				
			return "unpass";
			}
	public String unpass00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
         int uid=existUser.getUid();
         
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state", -2)) 
				                                                                   .addOrder(Order.desc("leadtime"));
		
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图

		}
	
	/**
	 * 查询所有已提交文档
	 * @return
	 */
	public String checkedManagerDoc(){
				return "checkedDoc";
			}
	public String checkedManagerDoc00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
          int uid=existUser.getUid();
          
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).addOrder(Order.desc("mantime"));
		Disjunction dis= Restrictions.disjunction();
	            dis.add(Restrictions.eq("state", 2)) ;
	            dis.add(Restrictions.eq("state", 3));
	    detachedCriteria.add(dis);
	            
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图

	}
	
	/**
	 * 查询所有二级审核通过文档
	 * @return
	 */
	public String checkedManagerpass2(){
				return "checkedManagerpass2";
			}
	public String checkedManagerpass200() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
         int uid=existUser.getUid();
         
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state", 3))
				                                                                   .addOrder(Order.desc("mantime"));
		
	                                                                    
	           
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图
	}
	
	/**
	 * 管理员提交改变状态
	 * @return
	 */
	public String managerSubmitChangeState(){

		Document existDocument=	documentService.checkState(document.getDid());
		// 判断
		if (existDocument.getState() == 1 || existDocument.getState() == -2) {
			existDocument.setMantime(new Date());
			//提交成功，修改文档的状态
			existDocument.setState(2);
			documentService.update(existDocument);				
			
		}
	return "managerSubmitChangeState";
	}
	
	
	/**
	 * 管理员退回改变状态
	 * @return
	 */
	
	private String manreason;
	
	
	public String getManreason() {
		return manreason;
	}

	public void setManreason(String manreason) {
		this.manreason = manreason;
	}

	public String managerRollChangeState(){
		//action中获得jsp页面中input的值,即获得输入的退回原因	
		manreason = ServletActionContext.getRequest().getParameter("manreason");
		String did = ServletActionContext.getRequest().getParameter("did");
		//this.setManreason(ServletActionContext.getRequest().getParameter("manreason"));	
		System.out.println("document.getDid()"+document.getDid());
		System.out.println("did为"+did);
		System.out.println("manreason为"+document.getManreason());
		Document existDocument=	documentService.checkState(Integer.parseInt(did));
		// 判断修改退回原因
		if (existDocument != null & (existDocument.getState() == 1 || existDocument.getState() == -2)) {			
			existDocument.setManreason(manreason);
			//退回成功，修改文档的状态
			existDocument.setState(-1);	
			documentService.update(existDocument);	
	      }
		return NONE;
		
	//return "managerRollChangeState";
	}
	/**
	 * 审核不通过文档
	 */
	
	public String managercheckunpass(){
		return "managercheckunpass";
	}
	public String managercheckunpass00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        int uid=existUser.getUid();
        
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state",-1))
				                                                                   .addOrder(Order.desc("mantime"));
		
	                                                                    
	           
		//设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);//将查询条件注入pageBeanUtil
		//数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);
		
		JsonConfig jc=new JsonConfig();//使用JsonLib插件将数据转化成json数据格式返回			
		//不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] {"detachedCriteria","page","pageSize","documents","documentss"}); 
		 // 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,new ObjectJsonValueProcessor(new String[]{"cid","cname"},Category.class));
		
		JSONObject jsonObject=JSONObject.fromObject(pageBeanUtil,jc);//从其它对象转化成JSON对象
		String json=jsonObject.toString();
		System.out.println(json+"!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//后台将json数据写会前台
		
		return NONE;//查询json数据，不需要配视图
	}
}
