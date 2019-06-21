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

    <title>南昌大学教学文档管理系统</title>
    
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
    

    <!-- 1    jQuery的js包 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>

<!-- 2    css资源 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">

<!-- 3    图标资源 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css"> 

<!-- 4    EasyUI的js包 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
 
<!-- 5    本地语言 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript">
     function formatterJson(value,row,Index){
  	   //alert(value.cname);
  	   return value.cname
     }
      function formattertime(value,row,Index){
      	
      	//之前显示的是2017/3/12 上午11:20,用以下方法自定义显示的格式2017-03-12  01:20:09
      	var myDate = new Date(value.time);
      	var y=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
      	var m=myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
      	var d=myDate.getDate(); //获取当前日(1-31)
      	var h=myDate.getHours(); //获取当前小时数(0-23)
      	var min=myDate.getMinutes(); //获取当前分钟数(0-59)
      	var s=myDate.getSeconds(); //获取当前秒数(0-59)
      	return y+'-'+(m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d) + '  ' +(h < 10 ? ('0' + h) :h) + ':' + (min < 10 ? ('0' + min) : min)+ ':' + (s < 10 ? ('0' + s) : s);
      }//endfunction
     
      function formatterdownload(value, row, Index)
      {
      	//alert(value);
      	var str = "<a href='${ pageContext.request.contextPath }/document_download.action?uploadFileName="+value+"'><input type='button' value='下载'></a>";  
      	return str;         
      }

      function onclickFunction(values){
    	//  alert("123")
    	 // alert(values)
    	 
    	  var place=document.getElementById("place").value;
    	  window.location.href='${ pageContext.request.contextPath }/secdocument_place.action?did='+values+'&place='+place;
    	 // alert(place)
    	 // window.location= "${ pageContext.request.contextPath }/secdocument_place.action?did=values&place=place";
    	  //window.location.href='${ pageContext.request.contextPath }/secdocument_place.action?did=values&place=place';
         /*  var url = '${ pageContext.request.contextPath }/secdocument_place.action';
    	    $.ajax({
    	        type: "post",
    	        url: url,
    	        data: {"did":values,"place":place},  */
    	       /*  cache: false,
    	        async : true,//ajax为异步 */
    	         // dataType: "json",
    	      /*  success: function ()
    	        {  alert("保存成功！")
    	    	   window.location.reload(); //刷新当前页面 
    	        },  */
    	        /* error:function (XMLHttpRequest, textStatus, errorThrown) {      
    	            alert("请求失败！");
    	        } */
    	   // });
    	  //var str = "<a href='${ pageContext.request.contextPath }/managerdocument_managerRollChangeState.action?did="+value+"'></a>"
      }
      
      function formatterrollback(value, row, Index)
      {
    	//alert(value);value值就是did
    	// onclick= Function(alert(document.getElementById('place').value))
    	//alert(document.getElementById("place").value)
    	//var str = "<a href='${ pageContext.request.contextPath }/managerdocument_managerRollChangeState.action?did="+value+"&place="+document.getElementById('place').value+"'><input type='button' onclick= Function(alert(document.getElementById('place').value))  value='退回'></a>";  
      	var str = "<input type='submit' value='选择位置' onclick=onclickFunction("+value+")>";  
      	return str;         
      }
      
      function formatterrollbackreason(value, row, Index)
      {
      	//alert(value);
      	var str = "<input  id='place' style='font-family:'楷体';font-size:13px;width:140px;' name='place' type='text'  placeholder='请输入相应的房间号'/>";  
      	return str;         
      }
    
      function sort(a,b){//将时间转化为毫秒数进行排序
  		aa= Date.parse(new Date(a.time));
  		bb=Date.parse(new Date(b.time))
  		return (aa>bb?1:-1);
       }
      
      
      function sorttype(a,b){//将时间转化为毫秒数进行排序
  		aa= a.cid;
  		bb=b.cid;
  		return (aa>bb?1:-1);
       }
      
</script>
    
    
    
    
   
  </head>

  <body>

  <section id="container" >
     <%@ include file="menu.jsp" %>>
  
      <%@ include file="secleftlist.jsp" %>
     
      <section id="main-content" style="padding-top:35px;">
          <section class="wrapper" style="float:right; ">
               
						<h3 style="text-align: center;">待归档文档</h3>
                              
	                      <div class="desc">                     
                        <!--  <table id="grid"></table>   -->
	                         <%-- <form id="ff" method="post" class="easyui-form"  action="${ pageContext.request.contextPath }/managerdocument_managerRollChangeState.action?did="+row.did+"'"> --%>
	                         
	                         <table class="easyui-datagrid" 
							    data-options="url:'${pageContext.request.contextPath}/secdocument_secUnplace00.action',fitColumns:true,singleSelect:true, border : false,
				            rownumbers : true,pageList: [10,20,30],pagination : true,remoteSort:false,multiSort:true">
							    <thead>
									<tr>
										<th data-options="field:'dname',width:100">名称</th>
									    <th data-options="field:'category',width:100,formatter:formatterJson,sortable:true,sorter:sorttype">类型</th>
										<th data-options="field:'leadtime',width:230,formatter:formattertime,sortable:true,sorter:sort">提交时间</th>									    
										 <th data-options="field:'c',width:200,formatter:formatterrollbackreason">存放房间号</th>
										 <th data-options="field:'did',width:100,formatter:formatterrollback">选择位置</th>
									</tr>
							    </thead>
							</table>    
							        
	                      </div>
                     
                      </div>
                     
              </div>
          </section>
      </section>
 </section>



  </body>
</html>
