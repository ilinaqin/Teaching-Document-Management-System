<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- 引入struts2的标签 -->
 <%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>


<link href="${pageContext.request.contextPath}/css/bootstraptwo.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/styletwo.css" rel='stylesheet' type='text/css' />

<script type="text/javascript">
departments = new Object();
departments['信息工程学院']=new Array('计算机技术', '控制专业', '计算机科学与技术');
departments['理学院']=new Array('数学系', '物理系', '化学系');
departments['机电工程学院']=new Array('机械工程系', '动力工程系');

function set_department(academy, department)
{
    var ac, dv;
    var i, ii;

    ac=academy.value;
    dv=department.value;

    department.length=1;

    if(ac=='0') return;
    if(typeof(departments[ac])=='undefined') return;

    for(i=0; i<departments[ac].length; i++)
    {
       ii = i+1;
       department.options[ii] = new Option();
       department.options[ii].text = departments[ac][i];
       department.options[ii].value = departments[ac][i];
    }

}
</script>


</head>
<body>

<%@ include file="menu.jsp" %>
<div style="height:12px;"></div>
<%@ include file="leftlist.jsp" %>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/document_save.action?&page=1" method="post" enctype="multipart/form-data">

	<div class="form-grid1" style=" float:right;margin-top:98px; width:1030px;height:700px; margin-right:100px;padding-right:50px;">
						
							
							  
							  <div class="bottom-form">
										<div class="col-md-3 grid-form">
												<h5>院：</h5>
										</div>
										<div class="col-md-9 grid-form1">
	                                          <select name="academy" class="multiple" onChange="set_department(this, this.form.department);">
													    <option value="0">===请选择相应的院===</option>
													    <option value="信息工程学院">信息工程学院</option>
													    <option value="理学院">理学院</option>
													    <option value="机电工程学院">机电工程学院</option>
	                                           </select>
                                           </div>
                                           <div class="clearfix"></div>
                                   </div>   
                                   
                                   <div class="bottom-form">
										<div class="col-md-3 grid-form">
												<h5>系：</h5>
										</div>
										<div class="col-md-9 grid-form1">		     
										    <select   name="department" id="citys" class="multiple">
										    <option value="0">===请选择相应的系===</option>
										    </select>
										 </div>
										 <div class="clearfix"></div>									
							</div>
						  
							
							<div class="bottom-form">
								<div class="col-md-3 grid-form">
									<h5>文件名称</h5>
								</div>
								<div class="col-md-9 grid-form1">
								<input type="text" placeholder="Text" name="dname" >
								
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="bottom-form">
								<div class="col-md-3 grid-form">
									<h5>文档说明</h5>
								</div>
								<div class="col-md-9 grid-form1">
								<textarea placeholder="Content"  style="height:250px;"  name="ddescrip"></textarea>
								</div>
								<div class="clearfix"></div>
							</div>

							
							
						<div class="bottom-form">
								<div class="col-md-3 grid-form">
										<h5>文件类型</h5>
								</div>
										
								<div class="col-md-9 grid-form1">
									<select class="multiple"  name="category.cid">
									<s:iterator var="c" value="#session.cList">
									<option value="<s:property value="#c.cid"/>"><s:property value="#c.cname"/></option>
									 </s:iterator>
									</select>
								</div>							
									
							<div class="clearfix"></div>
					</div>



							<div class="bottom-form">
								<div class="col-md-3 grid-form">
									<h5>上传该文件</h5>
								</div>
								<div class="col-md-9 grid-form1">
									<input type="file" name="upload"/>
								</div>
								<div class="clearfix"></div>
							</div>
							
								<div class="clearfix"></div>
							
							<div class="bottom-form" style="float:right;margin-top:10px; width:300px; margin-right:20px;">
								<div class="col-md-3 grid-form">
									<h5>结果</h5>
								</div>
								<div class="col-md-9 grid-form1">
								<button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-angle-right"></i><i class="fa fa-angle-right"></i> 提交</button>
							<a href="${pageContext.request.contextPath}/document_rollback.action"><button type="reset" class="btn btn-sm btn-warning"><i class="fa fa-repeat"></i> 重置</button></a>
								</div>
								<div class="clearfix"></div>
							</div>
						
</div>
</form>
</body>
</html>