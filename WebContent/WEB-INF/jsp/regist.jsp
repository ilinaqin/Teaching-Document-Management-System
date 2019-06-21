<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- 引入struts2的标签 -->
 <%@ taglib uri="/struts-tags" prefix="s" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>新用户注册</title>
<link href="${pageContext.request.contextPath}/css/regist.css" rel="stylesheet" type="text/css" />
<script>
    function checkForm(){
    	//校验用户名
    	//获得用户名文本框的值
    	var username=document.getElementById("username").value;
    	if(username==null||username==''){
    		alert("用户名不能为空！");
    		return false;
    	}
    	//校验密码
    	//获得密码文本框的值
    	var password=document.getElementById("password").value;
    	if(password==null||password==''){
    		alert("密码不能为空!");
    		return false;
    	}
    	//校验二次密码
    	//获得密码文本框的值
    	var repassword=document.getElementById("repassword").value;
    	if(repassword!=password){
    		alert("两次密码不一致!");
    		return false;
    	}
    	//职称不能为空
    	var jobtitle=document.getElementById("jobtitle").value;
    	if(jobtitle==null||jobtitle==''){
    		alert("职称不能为空！");
    		return false;
    	}
    	//手机号码校验
    	
   
    }
    function checkUsername(){
		// 获得文件框的值:
		var username = document.getElementById("username").value;
       
		// 1.创建异步交互对象
		var xhr = createXmlHttp();
		// 2.设置监听
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){//描述一种"已加载"状态；此时，响应已经被完全接收
				if(xhr.status == 200){//200表示成功收到 
					document.getElementById("span1").innerHTML = xhr.responseText;
				}
			}
		}
		/**
		* 3.打开连接,在此，open里面的路径
		*    提交，去数据库中查询有没有这个用户，经过action调用service，service再调用dao进行查询用户名
		*/
		xhr.open("GET","${pageContext.request.contextPath}/user_findByName.action?time="+new Date().getTime()+"&username="+username,true);
		// 4.发送
		xhr.send(null);
	}
	
    /**
    *创建异步对象
    */
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
			  // 发送一个请求后，客户端无法确定什么时候会完成这个请求，所以需要用事件机制来捕获请求的状态，
		      // XMLHttpRequest对象提供了onreadyStateChange事件实现这一功能
			  xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer；表示当前浏览器不是IE浏览器
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{//当前浏览器为IE浏览器
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;

	}
	//js验证码图片切换
	  function change(){
	   var img1=document.getElementById("checkImg");
	   img1.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	  }  	
    
    </script>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    
</head>
<body>
<div class="header"  align="center" style="height:100px; background-image:url(${pageContext.request.contextPath}/image/ttoplogo.jpg);">		
     
            <!--logo start-->
            <a href="" class="logo" style="font-size:20px;  color:#FFF;"><b>南昌大学教学文档管理系统</b></a>
            <!--logo end-->
	   
</div>

           <div class="title">
						<strong>新用户注册</strong>USER REGISTER
					
					</div>
					

	<div class="center">
   
		
		<div class="centerr">			
				<div  style="font-family:楷体; font-size:18px; color:#F00;"><s:actionerror/></div>
					<form id="registerForm" action="${pageContext.request.contextPath}/user_regist.action" method="post" novalidate="novalidate" onsubmit="return checkForm();">
						<table>
							
                            <tr>
								<th>
									<span class="requiredField">*</span>用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20" onblur="checkUsername()"/>
									<span id="span1"></span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"/>
									<span><s:fielderror fieldName="password"/></span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>确认密码:
								</th>
								<td>
									<input id="repassword" type="password" name="repassword" class="text" maxlength="20" autocomplete="off"/>
								</td>
							</tr>
							
									<tr>
										<th>
											姓名:
										</th>
										<td>
												<input type="text" name="name" class="text" maxlength="200"/>
												<span><s:fielderror fieldName="name"/></span>
										</td>
									</tr>
                                 
                                 <tr>
								<th>
									<span class="requiredField">*</span>职称:
								</th>
								<td>
								 <select class="multiple" name="jobtitle" id="jobtitle" style="width:230px;height:30px;">
                                             <option value="教师">教师</option>
                                            <option value="管理员">管理员</option>                            
                                             <option value="院领导">院领导</option>
                                            <option value="秘书">秘书</option>
                        
                                  </select>
<!-- 									<input type="text" id="jobtitle" name="jobtitle" class="text" maxlength="200"> -->
<%-- 									<span><s:fielderror fieldName="jobtitle"/></span> --%>
								</td>
							</tr>
                                 
                                   
									
									<tr>
										<th>
											电话:
										</th>
										<td>
												<input type="text" name="phone" class="text" />
										</td>
									</tr>
									
									<tr>
										<th>
											地址:
										</th>
										<td>
												<input type="text" name="addr" class="text" maxlength="200"/>
												<span><s:fielderror fieldName="addr"/></span>
										</td>
									</tr>
								<tr>
									<th>
										<span class="requiredField">*</span>验证码:
									</th>
									<td>
										<span class="fieldSet">
											<input type="text" id="checkcode" name="checkcode" class="text captcha" maxlength="4" autocomplete="off"><img id="checkImg" class="captchaImage" src="${pageContext.request.contextPath}/checkImg.action" onclick="change()" title="点击更换验证码">
										</span>
									</td>
								</tr>
							
					       <tr>
                           <th>
                           <td>
                             <button style="width:180px; color:#0080FF; margin-top:15px;" type="submit" value="Submit">点击注册并登录</button>
                           </td>
                           </th>
                           </tr>
                        </table>
					</form>
			</div>
	</div>
</body></html>