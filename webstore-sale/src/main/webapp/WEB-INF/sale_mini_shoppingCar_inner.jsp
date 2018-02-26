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
	
	<!--购物车商品-->
	<div class="cart_pro">
		<h6>最新加入的商品</h6>
		<c:if test="${empty list_car }">
			购物车为空,快去购物吧!
		</c:if>
		<c:if test="${not empty list_car }">
			<c:forEach items="${list_car }" var="item">
				<div class="one border">
					<img  style="height: 40px; width: 60px;" src="upload/${item.shp_tp }">
					<span class="one_name">
						${item.sku_mch }
					</span>
						<font style="size: 7px">${item.tjshl }</font> 
					<span class="one_prece">
						<b>￥${item.sku_jg }</b><br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
			</c:forEach>
		
			<div class="gobottom">
				共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
				共计￥<span>20000</span>
				<button class="goprice">去购物车</button>
			</div>
		</c:if>
	</div>
		
</body>
</html>