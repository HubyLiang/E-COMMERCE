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

	<c:forEach items="${list_car }" var="item">
		<input type="checkbox" value="${item.shfxz }" ${item.shfxz==1?"checked":""} onchange="shoppingCar_change_shfxz(${item.id },${item.sku_id },this.checked)">
		<img alt="" src="upload/${item.shp_tp }" width="100px"> 
		${item.sku_mch }   ${item.sku_jg } <a href="javascript:shoppingCar_change_tjshl(${item.id },${item.sku_id }, ${item.tjshl-1});">-</a>   ${item.tjshl }   <a href="javascript:shoppingCar_change_tjshl(${item.id },${item.sku_id }, ${item.tjshl+1});">+</a> ${item.hj }  删除<br>
	</c:forEach>
	总金额:  ${sum}

</body>
</html>