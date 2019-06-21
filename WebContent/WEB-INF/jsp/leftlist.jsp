<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

<aside  style="position:relative;">
          <div id="sidebar"  class="nav-collapse " style="margin-top:40px;position:relative; height:850px;float:left;">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
               <li> 
              <form action="${pageContext.request.contextPath}/document_docSearch.action?&page=1" method="post" novalidate="novalidate" onsubmit="return checkForm();">
                           
          			<span style="font-size:12px;color:#CCC;">文档查询</span>       
                     <input  id="docsearch" style="font-size:12px;float:right;width:140px;"  name="docsearch"  placeholder="关键字查询" type="docsearch"/>  
                                      
                     <div style="margin-left:140px;font-size:12px; padding-top:15px;" > <input type="submit" value="查询" /></div>                       
                         
               </form>
                </li> 
              
              
              
              	  <s:iterator var="c" value="#session.cList" status="cc">
                  <li class="mt">
                      <a  href="${ pageContext.request.contextPath }/document_findByCid.action?cid=<s:property value="#c.cid"/>&page=1">
                          <i class="fa fa-dashboard"></i>
                         <span><s:property value="#c.cname"/></span>
                      </a>
                  </li>
                   </s:iterator>
                 
                    <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/document_upload.action" >
                          <i class="fa fa-book"></i>
                          <span>文档上传</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/document_unsubmit.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>待提交文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/document_checkunpass.action?page=1" >
                           <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>审核未通过文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/document_submited.action?page=1" >
                          <i class="fa fa-book"></i>
                          <span>已提交文档</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/document_uncheck.action?page=1" >
                          <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>尚未审核文档</span>
                      </a>
                  </li>
                   <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/document_checkpass1.action?page=1" >
                          <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>一级审核通过文档</span>
                      </a>
                  </li>
                  
                   <li class="sub-menu">
                      <a href="${ pageContext.request.contextPath }/document_checkpass2.action?page=1" >
                          <i style="padding-left:30px;" class="fa fa-dashboard"></i>
                          <span>二级审核通过</span>
                      </a>
                  </li>
                   
                  
                  <li class="sub-menu" style="padding-top:20px;">
                      <a href="${ pageContext.request.contextPath }/document_findPage.action?&page=1" >
                          <i class="fa fa-book"></i>
                          <span>返回首页</span>
                      </a>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>