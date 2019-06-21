package cn.itcast.QLN.document.action;

import java.awt.image.BufferedImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
//import javax.mail.Session;
import javax.management.loading.PrivateClassLoader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.xslt.AbstractAdapterElement;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.itextpdf.text.log.SysoCounter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import com.sun.mail.iap.Response;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.itcast.QLN.category.service.CategoryService;
import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.document.ObjectJsonValueProcessor;
import cn.itcast.QLN.document.service.DocumentService;
import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBean;
import cn.itcast.QLN.utils.PageBeanUtil;
import cn.itcast.QLN.utils.UtilTools;
import cn.itcast.QLN.utils.WordtoPdf;
import cn.itcast.QLN.utils.imgs2pdf;
import cn.itcast.QLN.utils.ppt2img;
import freemarker.core.ReturnInstruction.Return;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

public class DocumentAction extends ActionSupport implements ModelDriven<Document> {

	// 模型驱动使用的对象
	private Document document = new Document();

	public Document getModel() {
		return document;
	}

	// 注入service
	private DocumentService documentService;

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 接收当前页数，跳转的时候方便显示某一页的列表数据
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return this.page;
	}

	// 接收cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	/**
	 * 根据一级分类查询文件
	 */
	public String findByCid() {
		ActionContext actionContext = ActionContext.getContext();
		// get HttpServletRequest
		Map<String, Object> request = (Map) actionContext.get("request");
		request.put("cid", cid);
		return "findByCid";
	}

	public String finByCidJson() throws IOException {
		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		System.out.println("cid为：" + cid);
		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		System.out.println("当前页为：" + page);
		System.out.println("rows值为：" + rows);
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.eq("category.cid", cid))
				.addOrder(Order.desc("dtime"));
		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });

		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	public String upload() {

		return "upload";
	}

	/**
	 * 实现文件上传
	 */

	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	// get方法，是为了下载框显示相应的文件名字
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) throws Exception {

		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	// 保存文档的方法:
	public String save() throws IOException {
		// 将提交的数据添加到数据库中.
		// 从session中获取用户信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		// document.setUser(existUser);

		document.setDtime(new Date());
		// product.setImage(image);
		if (upload != null) {

			// System.out.println("1111111111"+document.getDname()+document.getUser().getUid());

			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath("/documents");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			// System.out.println("444444444444444"+uid);

			// document.getUser().setUid(uid);
			// 在document中虽然定义了user对象，但那个对象为空的，必须new一个对象方可传值
			User userbean = new User();
			// User userbean = document.getUser();
			userbean.setUid(uid);
			// System.out.println("userbean.Uid"+userbean.getUid());
			document.setUser(userbean);

			document.setPlace("尚未归档");
			document.setState(0);
			document.setDimg("documents/" + uploadFileName);
		}
		// 将数据保存到数据库
		documentService.save(document);

		// System.out.println("222222222222222"+document.getDname()+document.getUser().getUid());

		PageBean<Document> pageBean = documentService.findByPage(page, uid);
		// // 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "saveSuccess";
	}

	public String rollback() {
		return "rollback";
	}

	/***
	 * 实现文件下载
	 */
	// 定义输入流，名称和xml里面的<param name="inputName">的名称一样
	private InputStream inputStream;
	private String inputPath;// 下载文件目录

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	// 文件下载
	public String download() throws FileNotFoundException, Exception {

		// 转码，解决中文名字问题
		uploadFileName = new String(uploadFileName.getBytes("ISO-8859-1"), "UTF8");

		System.out.println("下载路径为：" + ServletActionContext.getServletContext().getRealPath(uploadFileName));
		String path = ServletActionContext.getServletContext().getRealPath(uploadFileName);

		// 根据文件夹路径和文件名，创建file文件
		File file = new File(path);
		// 用输入流读取文件
		inputStream = new FileInputStream(file);
		// return new BufferedInputStream(new
		// FileInputStream(path+"\\"+FileName));
		uploadFileName = new String(uploadFileName.getBytes(), "iso8859-1");

		return "download"; // 返回的是xml的对应的action的名称
	}

	// 文件预览
	public String docview1() throws FileNotFoundException, Exception {

		
		// 转码，解决中文名字问题
		uploadFileName = new String(uploadFileName.getBytes("ISO-8859-1"), "UTF8");
		System.out.println("uploadFileName"+uploadFileName);
		
		System.out.println("预览路径为：" + ServletActionContext.getServletContext().getRealPath(uploadFileName));
		String path = ServletActionContext.getServletContext().getRealPath(uploadFileName);

		// 根据文件夹路径和文件名，创建file文件
		File file = new File(path);
		long fileSize = file.length();
		String fileName = file.getName();
		String suffix1 = fileName.substring(fileName.lastIndexOf(".") + 1);

		// 将doc文件另存为一份pdf文件
		if (suffix1.equals("doc") || suffix1.equals("docx")) {
			WordtoPdf.extractDoc(path, path + ".pdf");
			File file2 = new File(path + ".pdf");
			long fileSize2 = file2.length();
			String fileName2 = file2.getName();
			String suffix = fileName2.substring(fileName2.lastIndexOf(".") + 1);
			// 用输入流读取文件
			inputStream = new FileInputStream(file2);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(UtilTools.returnContentType(suffix));
			response.setHeader("Content-Disposition", "filename=\"" + uploadFileName + ".pdf" + "\"");
			byte[] buffer = new byte[(int) fileSize2];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length
					&& (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}
			// 确保所有数据均被读取
			if (offset != buffer.length) {
				throw new IOException("Could not completely read file " + file2.getName());
			}
			response.getOutputStream().write(buffer);
			response.getOutputStream().flush();
			response.flushBuffer();
			response.getOutputStream().close();
		} else if (suffix1.equals("pptx")) {
			String path2 =path.substring(0, 91);
			System.out.println("path2为" + path2);
			
			
			
			//创建一个新的文件夹，并命名，每次PPT转化为图片集合的时候，
			//便新创建一个文件夹，避免所有的图片都在一个图片集合中
		 String filefName=UUID.randomUUID().toString().replace("-", "1");			
			File dir = new File(path2+"\\"+filefName);
			  dir.mkdirs(); 
			String path3=path2+filefName+"\\";
			//System.out.println("path3为" + path3);
			
			//PPT转化为图片集合
			Map<String,Object> map=ppt2img.converPPTXtoImage(path,path3,"jpg");//.readPPTX2007Text(path);//("E:\\ppt\\PMS2.0界面设计培训-20130507.pptx");
              
			 boolean converReturnResult=(Boolean) map.get("converReturnResult"); 
		        System.out.println("converReturnResult为"+converReturnResult); 
		        if(converReturnResult){//如果全部转换成功,则为true;如果有一张转换失败,则为fasle 
		            @SuppressWarnings("unchecked") 
		            //List<String> imgNames中 存储的只有图片的名字
		            List<String> imgNames=(List<String>) map.get("imgNames"); 
		        /*
		            for (String imgName : imgNames) { 
		            	
		                System.out.println("imgName为"+imgName); 
		                String path4 =path3+imgName;
		                System.out.println("path4"+path4);			              
		            } */
		            
		            //图片集合转化为pdf格式
		            imgs2pdf.toPdf(path3, path2+"new.pdf");
		            File files = new File(path2+"new.pdf");
					long fileSizes = files.length();
					String fileNames = files.getName();
					String suffix = fileNames.substring(fileNames.lastIndexOf(".") + 1);
					// 用输入流读取文件
					inputStream = new FileInputStream(files);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType(UtilTools.returnContentType(suffix));
					response.setHeader("Content-Disposition", "filename=\"" + uploadFileName + ".pdf" + "\"");
					byte[] buffer = new byte[(int) fileSizes];
					int offset = 0;
					int numRead = 0;
					while (offset < buffer.length
							&& (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
						offset += numRead;
					}
					// 确保所有数据均被读取
					if (offset != buffer.length) {
						throw new IOException("Could not completely read file " + files.getName());
					}
					response.getOutputStream().write(buffer);
					response.getOutputStream().flush();
					response.flushBuffer();
					response.getOutputStream().close();
		        }
		        
		} else {
			// 用输入流读取文件
			inputStream = new FileInputStream(file);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(UtilTools.returnContentType(suffix1));
			response.setHeader("Content-Disposition", "filename=\"" + uploadFileName + "\"");
			byte[] buffer = new byte[(int) fileSize];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length
					&& (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}
			// 确保所有数据均被读取
			if (offset != buffer.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
			response.getOutputStream().write(buffer);
			response.getOutputStream().flush();
			response.flushBuffer();
			response.getOutputStream().close();
		}
		return null; // 返回的是xml的对应的action的名称

	}

	/**
	 * 实现文件模糊查询
	 */

	private String docsearch;

	public String getDocsearch() {
		return docsearch;
	}

	public void setDocsearch(String docsearch) {
		this.docsearch = docsearch;
	}

	public String docSearch() {
		this.setDocsearch(ServletActionContext.getRequest().getParameter("docsearch"));
		System.out.println("docsearch为" + docsearch);

		ActionContext actionContext = ActionContext.getContext();
		// get HttpServletRequest
		Map<String, Object> request = (Map) actionContext.get("request");
		request.put("docsearch", docsearch);

		if ((docsearch.length() == 0) || (docsearch == null)) {
			// actionContext.getContext().put("queryError","查询失败，请重新输入");
			return "docSearcherr";
		} else {
			return "docSearch";
		}
	}

	public String docSearch00() throws IOException {
		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.like("ddescrip", "%" + docsearch + "%"))
				.addOrder(Order.desc("dtime"));

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台
		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 删除文档
	 */
	public String del() {
		// 根据id查询文档信息

		document = documentService.findByDid(document.getDid());
		// 删除文档
		String path = ServletActionContext.getServletContext().getRealPath("/" + document.getDimg());
		File file = new File(path);
		file.delete();
		// 删除数据库中文档记录:
		documentService.delete(document);
		// 页面跳转
		return "deleteSuccess";

	}

	/**
	 * 老师提交给管理员审核文档，实现文档信息状态的更改，提交之前状态为0，提交之后状态为1
	 * 
	 * @throws IOException
	 */
	public String changeState() {

		Document existDocument = documentService.checkState(document.getDid());
		// System.out.println("changeState()"+page);
		// 判断
		if (existDocument.getState() == 0 || existDocument.getState() == -1) {

			existDocument.setTeachersubtime(new Date());
			// System.out.println("时间为："+document.getTeachersubtime());
			// 提交成功，修改文档的状态
			existDocument.setState(1);
			documentService.update(existDocument);
			// this.addActionMessage("文档提交成功！");
		}
		return "submitdoc";

	}

	/**
	 * 显示所有待提交文档文档
	 */
	public String unsubmit() {
		return "unsubmit";
	}

	public String unsubmit00() throws IOException {
		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).addOrder(Order.desc("teachersubtime"));
		Disjunction dis = Restrictions.disjunction();
		dis.add(Restrictions.eq("state", 0));
		dis.add(Restrictions.eq("state", -1));
		detachedCriteria.add(dis);

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 显示所有审核不通过文档
	 */
	public String checkunpass() {
		return "checkunpass";
	}

	public String checkunpass00() throws IOException {
		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.eq("state", -1))
				.addOrder(Order.desc("mantime"));

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 分页查询，返回json数据
	 */
	int rows;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 查询总文档信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String pageQuery() throws IOException {

		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		System.out.println("当前页为：" + page);
		System.out.println("rows值为：" + rows);
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).addOrder(Order.desc("dtime"));
		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回

		/*
		 * //默认为false，即过滤默认的key,去掉此句没有什么影响 jc.setIgnoreDefaultExcludes(false);
		 * //setCycleDetectionStrategy 防止自包含，单独使此句也阔以，就是数据加载过慢
		 * jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		 * //setExcludes：排除需要序列化成json的属性,下面这句解决延迟加载产生异常的问题 jc.setExcludes(new
		 * String[]{"handler","hibernateLazyInitializer"});
		 */

		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// jc.registerJsonValueProcessor(Category.class, new
		// ObjectJsonValueProcessor(new
		// String[]{"cid","cname"},Category.class));

		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 查询老师已经提交文档信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String submited() {
		return "submited";
	}

	public String checkpss() throws IOException {

		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).addOrder(Order.desc("teachersubtime"));
		Disjunction dis = Restrictions.disjunction();
		dis.add(Restrictions.eq("state", 1));
		dis.add(Restrictions.eq("state", 2));
		dis.add(Restrictions.eq("state", 3));
		detachedCriteria.add(dis);

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 显示所有老师已经提交，但是未审核文档
	 */
	public String uncheck() {
		return "uncheck";
	}

	public String uncheck00() throws IOException {

		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.eq("state", 1))
				.addOrder(Order.desc("teachersubtime"));

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 显示所有老师已经提交，一级审核通过文档
	 */
	public String checkpass1() {
		return "checkpass1";
	}

	public String checkpss11() throws IOException {

		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.eq("state", 2)).addOrder(Order.desc("mantime"));

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 显示所有老师已经提交，二级审核通过文档
	 */
	public String checkpass2() {
		return "checkpass2";
	}

	public String checkpss22() throws IOException {

		// 获得此时登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		int uid = existUser.getUid();

		PageBeanUtil<Document> pageBeanUtil = new PageBeanUtil<Document>();
		pageBeanUtil.setPage(page);// 将页面传来的page和rows注入我们的pageBean对象中
		pageBeanUtil.setPageSize(rows);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Document.class)
				.add(Restrictions.eq("user.uid", uid)).add(Restrictions.eq("state", 3))
				.addOrder(Order.desc("leadtime"));

		// 设置detachedCriteria查询Document对应的数据表
		pageBeanUtil.setDetachedCriteria(detachedCriteria);// 将查询条件注入pageBeanUtil
		// 数据封装完毕，用service来查询数据库完善pageBeanUtil需要返回响应的参数
		documentService.pageQuery(pageBeanUtil);

		JsonConfig jc = new JsonConfig();// 使用JsonLib插件将数据转化成json数据格式返回
		// 不加下面这句话的话，也能正确执行，就是数据加载超级慢，下面这句话是过滤的意思
		jc.setExcludes(new String[] { "detachedCriteria", "page", "pageSize", "documents", "documentss" });
		// 把列表显示需要的实体属性传过去,调用registerJsonValueProcessor构造方法，初始化参数
		jc.registerJsonValueProcessor(Category.class,
				new ObjectJsonValueProcessor(new String[] { "cid", "cname" }, Category.class));

		JSONObject jsonObject = JSONObject.fromObject(pageBeanUtil, jc);// 从其它对象转化成JSON对象
		String json = jsonObject.toString();
		System.out.println(json + "!!!!!!!!!!!!!!");
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);// 后台将json数据写会前台

		return NONE;// 查询json数据，不需要配视图
	}

	/**
	 * 查询显示所有文档
	 */
	public String findPage() {
		return "findPage";
	}

}
