package cn.itcast.QLN.document.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

public class LeaderDocumentAction extends ActionSupport implements ModelDriven<Document>  {
	
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

	int page;
	
	

	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}

	
	/**
	 * 显示所有领导相关的文档，即为显示所有的文档信息
	 * @return
	 */
    int rows;
    
	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public String findLeaderPage(){
		return "findLeaderPage";
	}
	public String findLeaderPage00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
          int uid=existUser.getUid();
          
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).addOrder(Order.desc("mantime"));
		Disjunction dis= Restrictions.disjunction();
		        dis.add(Restrictions.eq("state", -2)) ;
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
	 * 显示所有领导已经审核的的文档信息
	 * @return
	 */
	public String leaderChecked(){
		return "leaderChecked";
	}
	public String leaderChecked00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        int uid=existUser.getUid();
        
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state", 3)) 
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
	 * 显示所有领导待审核的的文档信息
	 * @return
	 */
	public String uncheckLeaderDoc(){
		return "uncheckLeaderDoc";		
	}
	public String uncheckLeaderDoc00() throws IOException{
		//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        int uid=existUser.getUid();
        
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state", 2)) 
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
	 * 领导提交文档改变文档状态
	 */
	public String leaderChangeState(){
		
		Document existDocument=	documentService.checkState(document.getDid());
		// 判断
		if (existDocument.getState() == 2) {
			existDocument.setLeadtime(new Date());
			//提交成功，修改文档的状态
			existDocument.setState(3);
			documentService.update(existDocument);				
			//this.addActionMessage("文档提交成功！");
		}
		return "leaderChangeState";
	}
	
	/**
	 * 领导退回文档改变状态的信息，并写入退回原因
	 */
	
	private String leadreason;
	
	
	public String getLeadreason() {
		return leadreason;
	}


	public void setLeadreason(String leadreason) {
		this.leadreason = leadreason;
	}


	public String leaderRollbackChangeState(){
		
		
		//action中获得jsp页面中input的值,即获得输入的退回原因	
		       leadreason = ServletActionContext.getRequest().getParameter("leadreason");
				String did = ServletActionContext.getRequest().getParameter("did");
				//this.setManreason(ServletActionContext.getRequest().getParameter("leadreason"));	
				System.out.println("document.getDid()"+document.getDid());
				System.out.println("did为"+did);
				System.out.println("manreason为"+document.getLeadreason());
				//Document existDocument=	documentService.checkState(Integer.parseInt(did));
			
		//action中获得jsp页面中input的值,即获得输入的退回原因	
		//this.setLeadreason(ServletActionContext.getRequest().getParameter("leadreason"));	
		
		Document existDocument=	documentService.checkState(Integer.parseInt(did));
		// 判断修改退回原因
		if (existDocument != null & existDocument.getState() == 2) {			
			existDocument.setLeadreason(leadreason);
			//退回成功，修改文档的状态
			existDocument.setState(-2);	
			documentService.update(existDocument);	
	      }
		
		
		return "leaderRollbackChangeState";
	}
	/**
	 * 显示 所有审核未通过文档
	 * @return
	 */
	public String leaderUnpass(){
		return "leaderUnpass";
	}
	public String leaderUnpass00() throws IOException{
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
}
