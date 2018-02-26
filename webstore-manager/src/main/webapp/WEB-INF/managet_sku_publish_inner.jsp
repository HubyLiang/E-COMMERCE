<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${list_object_attr_value}" var="object_attr_value" varStatus="index_attr">
		<input onclick="sku_publish_show_value(this.value,this.checked)"  type="checkbox" name="list_attr[${index_attr.index}].id" value="${object_attr_value.id}"/>${object_attr_value.shxm_mch}
	</c:forEach>
	<hr>
	<c:forEach items="${list_object_attr_value}" var="object_attr_value" varStatus="index_attr">
		<div style="display:none;" id="sku_publish_show_value_${object_attr_value.id}">
			<c:forEach items="${object_attr_value.list_value}" var="val" varStatus="index_value">
				<input  type="radio" value="${val.id}" name="list_attr[${index_attr.index}].list_value[0].id"/>:${val.shxzh}${val.shxzh_mch}
			</c:forEach>
		</div>
		<br>
	</c:forEach>
	<br>
	sku名称:<input type="text" name="sku_mch"/><br>
	sku价格:<input type="text" name="jg"/><br>
	库存数量:<input type="text" name="kc"/><br>
	库存地址:<input type="text" name="kcdz"/><br>
	<input type="submit" value="发布sku"/>
	
	
	<script type="text/javascript">
	function sku_publish_show_value(object_attr_value_id,checked){
		if(checked){
			$("#sku_publish_show_value_"+object_attr_value_id).show();	
		}else{
			$("#sku_publish_show_value_"+object_attr_value_id).hide();
		}
	}
</script>
</body>
</html>