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

	<c:forEach items="${list_address}" var="address">
		${address.yh_dz} ${address.shjr}<br>
	</c:forEach>
	<hr>
	<form action="add_address.do" method="post">
		<input type="hidden" value="${user.id}" name="yh_id"/>
		用户地址：<input type="text" name="yh_dz"/><br>
		收件人：<input type="text" name="shjr"/><br>
		联系方式：<input type="text" name="lxfsh"/><br>
		<input type="submit" value="提交"/>
	</form>

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>