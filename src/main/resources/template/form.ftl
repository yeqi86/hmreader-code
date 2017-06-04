<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Templates/admin/head.jsp"%>

<!-- 顶部的功能菜单 -->
<div class="header lr10">
	<c:if test=""${r"${"}oper_fg eq '0' ${r"}"}">
	<h3 class="nav_icon">添加</h3>
	<span class="span_fenge lr10"></span>
	</c:if>
	
	<c:if test=""${r"${"}oper_fg eq '1' ${r"}"}">
	<h3 class="nav_icon">编辑</h3>
	<span class="span_fenge lr10"></span>
	</c:if>
	
	<a href="javascript:history.go(-1)">返回</a>
	
</div>


<div class="het5"></div>

<div class="table_form lr10">
	<form method="post" id="myform" enctype="multipart/form-data" method="post" >
	<input type="hidden" name="id" value=""${r"${"}item.id${r"}"}">
	<input type="hidden" name="oper_fg" value=""${r"${"}oper_fg${r"}"}">
	
	<table width="100%" class="lr10">
		<tr>
			<td width="150"><span class="xinghao_css">*</span>id</td>
			<td>
				<input type="text" class="input-text wid250" name="id" value="${r"${"}item.id ${r"}"}"
					datatype="*" errormsg="请输入 主文件名" nullmsg="请输入主文件名">						
			</td>
			</td>
		</tr>
				
	</table>
	
	<div class="bk15"></div>
    <input type="submit" class="button" value="提交" />
	<input type="button" class="button" value="返回" onclick="javascript:history.go(-1)"/>   
    
	</form>
</div>

</script>


<%@ include file="/Templates/admin/foot.jsp"%>