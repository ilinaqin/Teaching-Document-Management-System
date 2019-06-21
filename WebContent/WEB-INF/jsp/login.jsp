<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <!-- 引入struts2的标签 -->
 <%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>南昌大学研究教学文档管理系统登录</title>
    <style type="text/css">
        body {
            background: #0d59ac;
            font-size: 12px;
        }

        .STYLE6 {
            color: #ffffff;
        }

        .code {
            cursor: pointer;
        }

        .textmc {
            MARGIN-TOP: 3px;
            TEXT-INDENT: 2px;
            MARGIN-BOTTOM: 3px;
            COLOR: #000000;
            VERTICAL-ALIGN: middle;
            LINE-HEIGHT: 16px;
            FONT-FAMILY: "宋体";
            COLOR: #035551;
            FONT-SIZE: 12px;
            BORDER-BOTTOM: #1f7d78 1px solid;
            BORDER-LEFT: #1f7d78 1px solid;
            LINE-HEIGHT: 16px;
            HEIGHT: 18px;
            width: 130px;
            BORDER-TOP: #1f7d78 1px solid;
            BORDER-RIGHT: #1f7d78 1px solid;
        }
    </style>

</head>
<body>
    
    <div align="center" style="width:66%; margin-left:17%;">
    
     <div align="center" style=" background-image:url(${pageContext.request.contextPath}/image/loginbackgroundimg.jpg);width: 696px; height:385px;  margin-top:50px;" >
           
          <table width="121px"  height="121px" border="0" cellpadding="0" cellspacing="0" align="left" style="padding-left:12%; padding-top:15%;">
            <tr>
               <td id="tdimg"><img src="${pageContext.request.contextPath}/image/adminlogo.jpg" width="121" height="121" /></td>
            </tr>
          </table>
       
       <div  style="font-family:楷体; font-size:18px; color:#F00;"><s:actionerror/></div>
       
      <form id="loginForm" action="${pageContext.request.contextPath}/user_login.action?page=1" method="post" novalidate="novalidate" onsubmit="return checkForm();">
         <table width="300" border="0" cellpadding="0" cellspacing="0" align="right" style="padding-top:120px; padding-right:50px">
               <tr style="height: 28px;">
                   <td align="right">
                     <span class="STYLE6">用户名称：&nbsp;&nbsp;</span>
                   </td>
                   <td>
                      <input name="username" type="text" id="username" class="textmc" reg="\S" title="登录用户名称不能为空，请输入！" />
                    </td>
                </tr>
                <tr style="height: 28px;">
                    <td align="right">
                         <span class="STYLE6">用户密码：&nbsp;&nbsp;</span>
                     </td>
                     <td>
                        <input name="password" type="password" id="password" class="textmc" reg="^\S" title="登录用户密码不能为空，请输入" autocomplete="off" />
                       </td>
                </tr>
               
                <tr style="height: 28px;">
                     <td align="right">
                       <span class="STYLE6">身<span style="padding-left: 24px;"></span>份：&nbsp;&nbsp;</span>
                      </td>
                      <td>
                          <select name="selectJobtitle" id="ddlUserClass" style="width:134px;">
                            <option value="教师">教师</option>
                            <option value="管理员">管理员</option>                            
                            <option value="院领导">院领导</option>
                            <option value="秘书">秘书</option>
                        
                          </select>
                       </td>
               </tr>
               <tr style="height: 28px;">
                       <td align="right">
                          <span class="STYLE6">验<span style="padding: 0px 6px;">证</span>码：&nbsp;&nbsp;</span>
                       </td>
                       <td>
             <div>
             
             <div style="float: left;">
                        <input name="checkcode" type="text" id="checkcode" class="text captcha" maxlength="4" style="width: 65px; height: 18px" />
                    </div>
             &nbsp; &nbsp; &nbsp; 
             
            <img id="checkImg"  class="captchaImage" src="${pageContext.request.contextPath}/checkImg.action" style="width: 80px; height: 25px" onclick="change()" title="点击更换验证码">
                  
              </img>    
                  
                  
                  
              </div>
             </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center" style="padding-right: 32px; padding-top: 10px;">
                                <input type="image" class="submit" name="ctl00" src="${pageContext.request.contextPath}/image/Login_12.gif" onclick="return CheckForm();" style="height:23px;width:87px;border-width:0px;" />
                                <span style="padding-left: 28px;"></span>
                                <input type="image" name="ImageButton1" id="ImageButton1" src="${pageContext.request.contextPath}/image/02_03.gif" style="height:23px;width:87px;border-width:0px;" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" align="center">
                                <br />
                            </td>
                        </tr>
                        <tr>
                          
                            <td colspan="2" align="right"  style="text-align: right; padding-top:20px;">
                                <a href="${pageContext.request.contextPath}/user_registPage.action" target="_blank" style="color: white; text-decoration: none;">新用户请在此点击进入注册页面>></a>
                            </td>
                        </tr>
                    </table>
                  </form>
                   <span  style="font-size:18px; color:#F00; font-family:'楷体';"> <s:fielderror/></span>
      </div>        
      
           
            <div style="color:#fff;padding-top:5px;float:left; text-align:left;">&nbsp;登录说明：新生初次登录，登录名密码均为学号，老生登录名为学号，密码为123456。教职工初次登录，用户名：工资号（纯数字不足6位的，前面加0）；原始密码：工资号（纯数字不足6位的，前面加0）（登陆系统后请自行修改密码）。</div>
 
  </div>

    <script type="text/javascript" language="javascript">

        $(document).ready(function () {
            $(".dLogin").css("padding-top", ($(window).height() - 360) / 2);

            $("input[name='username']").eq(0).focus();

            $("#Image1").click(function () {
                var d = new Date();
                $(this).attr("src", "Image.aspx?" + d + d + d);
            });
        });

      //js验证码图片切换
  	  function change(){
  	   var img1=document.getElementById("checkImg");
  	   img1.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
  	  }  
        
        function checkForm() {
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
        	
        }
        // 顯示讀取遮罩
        function ShowProgressBar() {
            displayProgress();
            displayMaskFrame();
        }

        // 隱藏讀取遮罩
        function HideProgressBar() {
            var progress = $('#divProgress');
            var maskFrame = $("#divMaskFrame");
            progress.hide();
            maskFrame.hide();
        }
        
       
    </script>

</body>
</html>
