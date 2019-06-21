<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

<aside  style="position:relative;">
          <div id="sidebar"  class="nav-collapse " style="margin-top:40px;position:relative; height:510px;float:left;">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
            
                    <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/leaderdocument_uncheckLeaderDoc.action?&page=1" >
                          <i class="fa fa-book"></i>
                          <span>待审核文档</span>
                      </a>
                  </li>
                  
                   
                  <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/leaderdocument_leaderChecked.action?&page=1" >
                          <i class="fa fa-book"></i>
                          <span>审核通过文档</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/leaderdocument_leaderUnpass.action" >
                          <i class="fa fa-book"></i>
                          <span>审核不通过文档</span>
                      </a>
                  </li>
                  
                 
                  
                   <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/leaderdocument_findLeaderPage.action?&page=1" >
                          <i class="fa fa-book"></i>
                          <span>返回首页</span>
                      </a>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>