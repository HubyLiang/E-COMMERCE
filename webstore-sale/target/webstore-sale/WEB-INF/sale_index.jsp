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

	<c:if test="${not empty user }">
		欢迎登陆,${user.yh_nch } , <a href="goto_logout.do" target="_blank">登出</a>
	</c:if>
	<c:if test="${empty user }">
		请<a href="goto_regist.do" target="_blank">注册 </a>  <a href="goto_login.do" target="_blank">登陆</a>
	</c:if>
	<hr>
	<h2>前台交易端页面</h2>
	<ul id="index_lass_1_li"></ul>
	<ul id="index_lass_2_li"></ul>

	sale_index
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(function(){
		//读取一级分类js数据
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#index_lass_1_li").append("<li value="+json.id+"  onmouseover='spu_publish_select_class_2_by_class_1_id(this.value)' style='width:70px'>"+json.flmch1+"</li>");
			});
		});
		//加载到一级分类的下拉列表中
	});
	
	function spu_publish_select_class_2_by_class_1_id(class_1_id){
		// 获得被选中的一级分类id
		// var class_1_id = $("#index_lass_1_li option:selected").val();
		
		// 根据一级分类id加载二级分类和商品信息
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_lass_2_li").empty();
			$(data).each(function(i,json){
				$("#index_lass_2_li").append("<li value="+json.id+"> <a href='goto_search_index/"+json.id+"/"+json.flmch2+".do' target='_blank'>"+json.flmch2+"</a> </li>");
			});
		});
	}
	</script>
</body>
</html>