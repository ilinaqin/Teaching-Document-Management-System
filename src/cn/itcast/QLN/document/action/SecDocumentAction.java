package cn.itcast.QLN.document.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.lf5.viewer.TrackingAdjustmentListener;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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

public class SecDocumentAction extends ActionSupport implements ModelDriven<Document> {
	
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
 * 返回首页显示所有领导审核通过文档的信息
 */
int rows;

public int getRows() {
	return rows;
}

public void setRows(int rows) {
	this.rows = rows;
}

public String allSecDoc(){
	return "findSecPage";
}
public String allSecDoc00() throws IOException{
	//获得此时登录用户的信息
	 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
   int uid=existUser.getUid();
   
	
	PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
	pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
	pageBeanUtil.setPageSize(rows);
	
	DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state",3))
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
 * 显示所有待归置文档
 */
public String secUnplace(){
	return "secUnplace";
}

public String secUnplace00() throws IOException{
	//获得此时登录用户的信息
	 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
  int uid=existUser.getUid();
  
	
	PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
	pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
	pageBeanUtil.setPageSize(rows);
	
	DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state",3))
			                                                                   .add(Restrictions.eq("place", "尚未归档"))
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
 * 显示所有已归档文档
 */
public String  secPlaced(){	
	return "secPlaced";
}

public String  secPlaced00() throws IOException{
	//获得此时登录用户的信息
		 User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
	   int uid=existUser.getUid();
	   
		
		PageBeanUtil<Document> pageBeanUtil=new PageBeanUtil<Document>();			
		pageBeanUtil.setPage(page);//将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Document.class).add(Restrictions.eq("state",3))
				                                                                   .add(Restrictions.ne("place", "尚未归档"))
				                                                                   .addOrder(Order.desc("sectime"));
		
	                                                                   
	          
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
 * 归档;跳转到选择位置页面
 */

public String place(){
	String did = ServletActionContext.getRequest().getParameter("did");
	String place = ServletActionContext.getRequest().getParameter("place");
	
	System.out.println("获取当前连接中的did，方便传送到前台，值为" + did);
	System.out.println("房间号place为：" + place);
	ActionContext actionContext = ActionContext.getContext();
	// get HttpServletRequest
	Map<String, Object> request = (Map) actionContext.get("request");
	request.put("did", did);
	System.out.println("request.get(key):"+request.get("did"));
	
	Map<String,Object> session = ActionContext.getContext().getSession();
	session.put("place", place);
	System.out.println("request.get(place):"+session.get("place"));
	
    return "showplace";

}

/**
 * 归档;跳转到选择位置页面
 */
private String place;

public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
}

public String setplace(){
	place = ServletActionContext.getRequest().getParameter("place");
	System.out.println("存放位置为："+place);
	String did = ServletActionContext.getRequest().getParameter("did");
	System.out.println("传送过来的did值为 ："+did);
	//this.setManreason(ServletActionContext.getRequest().getParameter("place"));	
	System.out.println("document.getDid()"+document.getDid());
	System.out.println("did为"+did);
	System.out.println("Place为"+document.getPlace());
	Document existDocument=	documentService.checkState(Integer.parseInt(did));
	
	//action中获得jsp页面中input的值,即获得输入的归档位置	
	//this.setPlace(ServletActionContext.getRequest().getParameter("place"));	
    
	// 判断
	if (existDocument != null) {
		existDocument.setSectime(new Date());
		existDocument.setPlace(place);
		documentService.update(existDocument);
	}
	return NONE;
}


}
