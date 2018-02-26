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
	<c:forEach items="${list_object_sku }" var="object_sku">
		<div style="float:left;width:300px;height:300px;">
			<a href="go_sku_detail/${object_sku.id }/${object_sku.shp_id}.do">
				<img alt="" src="upload/${object_sku.spu.shp_tp }" style="width: 150px;height: 150px">	<br>	
				sku名称: ${object_sku.sku_mch } <br> 
				sku销量: ${object_sku.sku_xl }<br> 
				sku价格: ${object_sku.jg }<br>
				sku创建时间: ${object_sku.chjshj }<br>
			</a>
		</div>
	</c:forEach>
</body>
</html>