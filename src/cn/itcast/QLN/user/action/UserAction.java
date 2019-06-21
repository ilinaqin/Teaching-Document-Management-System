package cn.itcast.QLN.user.action;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.commons.collections.functors.NonePredicate;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.RequestAttributes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.QLN.category.service.CategoryService;
import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.document.service.DocumentService;
import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.service.UserService;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBean;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	/**
	 * 使用模型驱动接受user对象数据，此时便可以接收到用户名
	 */
	private User user=new User();	

	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//手动接收验证码
		private String checkcode;
		public void setCheckcode(String checkcode) {
			this.checkcode = checkcode;
		}
	
	public String registPage(){
		return "registPage";
	}	
	//注入userservice
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//注入categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 
	 *注册时候 异步校验用户名是否存在
	 * @return
	 * @throws IOException
	 */
	public String findByName() throws IOException{
		 /**
		  * 调用Service进行查询
		  */
		User existUser=userService.findByUsername(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;Charset=UTF-8");
		//判断
		if(existUser!=null){
			//查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
			}
		else{
			//没有查到该用户：用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	
	
	/**
	 * 用户注册的方法，之后直接返回登录页面
	 * @return
	 */
  public String regist(){
		 //判断验证码程序
		 //session中获得验证码的随机值
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1))
		{this.addActionError("您的验证码输入错误，请重新输入！");
		return "checkcodeFail";
		}
		
	  userService.save(user);
	  return LOGIN;
  }
 
  
  //接收当前页数，跳转的时候方便显示第一页的列表数据
  private int page;
public void setPage(int page) {
	this.page = page;
}

//注入documentService
   private DocumentService documentService;
	public void setDocumentService(DocumentService documentService) {
	this.documentService = documentService;
}
	
	
  public String login(){
	  //从jsp页面获得相应的职称
	  String selectJobtitle=ServletActionContext.getRequest().getParameter("selectJobtitle");
	  
	  User existUser=userService.login(user,selectJobtitle);
	  
	  //判断验证码程序
		 //session中获得验证码的随机值
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1))
		{this.addActionError("您的验证码输入错误，请重新输入！");
		return "logincheckcodeFail";
		}
	//判断
		//System.out.println(">>>>>>>>>>>>>>>>>"+existUser.getjobtitle());
		if(existUser==null){
			System.out.println("没有用户名");
			//登录失败			
		   addFieldError("message", " 登录失败，用户名或密码或职称错误，请重新登录！");
			return LOGIN;
		}else if (existUser.getjobtitle().equals("教师")) { 			
				//查询所有 一级分类的集合
				List <Category> cList=categoryService.findAll();
				//将一级分类数据存入session范围
				ActionContext.getContext().getSession().put("cList", cList);
				
				
				//System.out.println("2222222222222"+existUser.getUid());
				PageBean<Document> pageBean = documentService.findByPage(page,existUser.getUid());
				// 将PageBean存入到值栈中:
				ActionContext.getContext().getValueStack().set("pageBean", pageBean);
				
				//登录成功
				//将用户信息存入到session中 
				ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
				//页面跳转
				return "teacherLoginSuccess";
			}
		else if (existUser.getjobtitle().equals("管理员")){
					
			//登录成功
			//将用户信息存入到session中 
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "managerLoginSuccess";
		   }	
		else if (existUser.getjobtitle().equals("院领导")){
			
			//登录成功
			//将用户信息存入到session中 
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "leaderLoginSuccess";
		}
		else{
			
			//登录成功
			//将用户信息存入到session中 
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "secLoginSuccess";
		}
		
	}
  
  
  
  public String quit(){
	  
	  return "quit";
  }

  
}
	


