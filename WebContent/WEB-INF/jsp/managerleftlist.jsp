<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

<aside  style="position:relative;">
          <div id="sidebar"  class="nav-collapse " style="margin-top:40px;position:relative; height:520px;float:left;">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
            
                    <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/managerdocument_uncheckManagerDoc.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>待审核文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/managerdocument_unpass.action?page=1" >
                          <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>二级审核未通过</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/managerdocument_checkedManagerDoc.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>审核通过文档</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/managerdocument_checkedManagerpass2.action?page=1" >
                           <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>二级审核通过文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/managerdocument_managercheckunpass.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>审核不通过文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/managerdocument_findManagerPage.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>返回首页</span>
                      </a>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>