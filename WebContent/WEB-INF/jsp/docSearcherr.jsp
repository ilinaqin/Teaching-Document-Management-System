<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- 引入struts2的标签 -->
 <%@ taglib uri="/struts-tags" prefix="s" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>南昌大学研究教学文档管理系统登录</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    

    <!-- Bootstrap core CSS -->
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


    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
    <%@ include file="menu.jsp" %>>
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <%@ include file="leftlist.jsp" %>
      <!--sidebar end-->


      <!--main content start-->
      <section id="main-content" style="padding-top:35px;">
          <section class="wrapper" style="float:right; ">
               
                  
                 
                    <!--COMPLETED ACTIONS DONUTS CHART-->
						<h3 style="text-align: center;">文档查询</h3>
                                        
                      <!-- First Action -->
                      <div class="desc">
                      
                      	<table class="table" >
                          <tr class="tline">
                         <th class="tname" style="font-size:16px;  width:200px; padding-left:50px;">名称</th>
                         <th class="ttype" style="font-size:16px;  width:200px; padding-left:50px;">类型</th>
                         <th class="ttime" style="font-size:16px;  width:200px; padding-left:50px;">上传时间</th> 
                         <th class="tdownload" style="font-size:16px; width:200px; padding-left:100px;">下载</th>
                         </tr>
                          </table> 
                       
             <div style=" background-image:url(${pageContext.request.contextPath}/image/tipbackimg.jpg);  margin-left:160px;height:360px; width:510px;">
<div  style="font-family:楷体; font-size:26px; color:#Fff; text-align:center; padding-top:160px; color:#f00"> 没有搜索到该文件，请重新正确输入!!</div>
</div>
                            
                                                        
                       
                      </div>
                      <!-- Second Action -->
                   
                      <!-- Third Action -->
                     
                      <!-- Fourth Action -->
                      
                      </div>
                      <!-- Fifth Action -->



                      
                
              </div><! --/row -->
          </section>
      </section>

      <!--main content end-->

  </section>

    <!-- js placed at the end of the document so the pages load faster -->

  </body>
</html>
