<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Templates/admin/head.jsp"%>

<!-- 顶部的功能菜单 -->
<div class="header lr10">
	<auth:haveAuth link="/admin/${class?uncap_first}/index">
	<h3 class="nav_icon"><a href="${r"${"}pageContext.request.contextPath ${r"}"}${r"${"}auth_murl ${r"}"}">${r"${"}auth_opername ${r"}"}</a></h3>
	<span class="span_fenge lr10"></span>
	</auth:haveAuth>
	
	<auth:haveAuth link="/admin/${class?uncap_first}/add">
	<a href="${r"${"}pageContext.request.contextPath ${r"}"}${r"${"}auth_murl ${r"}"}">${r"${"}auth_opername ${r"}"}</a>
	<span class="span_fenge lr5">|</span>
	</auth:haveAuth>

</div>
<div class="het5"></div>
	
<!-- 查询 -->	
<div class="header-data lr10">
	<form id="queryDataForm" method="get" >
	   
	       主文件名：<input type="text" name= class="input-text" value=""/>
	                   
	    <input type="submit" value="搜索" class="button"/>
	    
	</form>
</div>	
<div class="het5"></div>

<div class="lr10">
	<table class="tablelist_new">
		<thead>
			<tr>
				<#list properties as prop>
                 <th> ${prop.fieldName}</th>
                 </#list>
				<th>其他</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${r"${"}dataList  ${r"}"}" var="item">
		<tr>
			<#list properties as prop>
                 <td>${r"${"}item.${prop.fieldName} ${r"}"}</td>
            </#list>
			<td>
				<auth:haveAuth link="/admin/${class?uncap_first}/edit">
				<a href="${r"${"}pageContext.request.contextPath ${r"}"}${r"${"}auth_murl ${r"}"}/${r"${"}item.id${r"}"}"
					class="tablelink">${r"${"}auth_opername ${r"}"}</a>
				</auth:haveAuth>
				<auth:haveAuth link="/admin/${class?uncap_first}/delete">
				<a onclick="window.parent.deleteConfirm(this,'确定要删除吗？','${r"${"}pageContext.request.contextPath ${r"}"}${r"${"}auth_murl ${r"}"}/${r"${"}item.id${r"}"}')"
					href="javascript:;" class="tablelink">${r"${"}auth_opername ${r"}"}</a>
				</auth:haveAuth>
			</td>
		</tr>
		</c:forEach>
		</tbody>
		
	</table>

	<%@ include file="../../pagePublic/admin_pagination.jsp" %>

</div>

<%@ include file="/Templates/admin/foot.jsp"%>