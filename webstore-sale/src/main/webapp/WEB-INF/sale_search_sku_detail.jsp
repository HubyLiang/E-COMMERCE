<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	${object_sku.sku_mch }<br>
	${object_sku.jg }<br>
	${object_sku.kc }<br>
	
	<img alt="" src="upload/${object_sku.spu.shp_tp }">
	<hr>
	<c:forEach items="${list_sku }" var="sku">
		<a href="go_sku_detail/${sku.id }/${sku.shp_id}.do">${sku.sku_mch }</a><br>
	</c:forEach>
	<hr>
	<!-- <a href="javascript:;">加入购物车</a> -->
	<form action="add_car.do" method="post">
		<input type="hidden" name="sku_mch" value="${object_sku.sku_mch }">
		<input type="hidden" name="sku_jg" value="${object_sku.jg }">
		<input type="hidden" name="tjshl" value="1">
		<input type="hidden" name="hj" value="${object_sku.jg }">
		<input type="hidden" name="shp_id" value="${object_sku.shp_id }">
		<input type="hidden" name="sku_id" value="${object_sku.id }">
		<input type="hidden" name="shp_tp" value="${object_sku.spu.shp_tp }">
		<input type="hidden" name="kcdz" value="${object_sku.kcdz }">
		<input type="submit"  value="添加购物车"><br>
	</form>
	<hr>
	<c:forEach items="${object_sku.list_attr_value_name }" var="val">
		${val.attr_name }: ${val.value_name }<br>
	</c:forEach>
	
	<hr>
	${object_sku.spu.shp_msh}

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>