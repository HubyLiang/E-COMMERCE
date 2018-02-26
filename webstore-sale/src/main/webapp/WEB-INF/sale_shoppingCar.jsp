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
<title>购物车</title>
</head>
<body>

	<div id="shopping_inner">
		<jsp:include page="sale_shoppingCar_inner.jsp"/>
	</div>



	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		function shoppingCar_change_shfxz(car_id, sku_id, shfxz){
			if(shfxz){
				shfxz = "1";
			}else{
				shfxz = "0";
			}
			$.post("shoppingCar_change.do",{"car_id":car_id,"sku_id": sku_id,"shfxz": shfxz,"tjshl":-1},function(data){
				$("#shopping_inner").html(data);
			})
		}
		
		function shoppingCar_change_tjshl(car_id, sku_id, tjshl){
			$.post("shoppingCar_change.do",{"car_id":car_id,"sku_id": sku_id,"shfxz": "","tjshl":tjshl},function(data){
				$("#shopping_inner").html(data);
			})
		}
	</script>
</body>
</html>