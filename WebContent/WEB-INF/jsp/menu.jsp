<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

      <!--header start-->
      <header class="header black-bg" style="height:100px; background-image:url(${pageContext.request.contextPath}/image/ttoplogo.jpg);">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="" class="logo"><b>南昌大学教学文档管理系统</b></a>
            <!--logo end-->
           
               <div style="width:200px; height:30px; float:right; margin-right:15px; margin-top:10px;">
                <ul>
				    <s:if test="#session.existUser!=null">
					<li id="headerLogin" class="headerLogin" style="display: list-item; font-family:'楷体'; color:#000; font-size:16px; padding-top:3px;">
						欢迎<s:property value="#session.existUser.name"/>的登录！
						</li>
					</s:if>
                     </ul>
            </div>
           
           
            <div class="top-menu" style="margin-top:40px;margin-right:5px;">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="${pageContext.request.contextPath}/user_quit.action" style="color:#FFF;">退出</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->