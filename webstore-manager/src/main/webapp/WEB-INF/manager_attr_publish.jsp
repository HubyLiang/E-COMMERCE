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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类属性发布</title>
</head>
<body>
	
		<select name="flbh1" id="attr_publish_class_1_select" onChange="attr_publish_select_class_2_by_class_1_id(this.value)"></select>
		<br>
		
		<select name="flbh2" id="attr_publish_class_2_select" onclick="attr_publish_get_attr_by_class_2_id(this.value)"></select>
		<br>
		<select name="pp_id" id="attr_publish_tm_select" ></select>
		<br>
		
		<div id="attr_publish_inner">
		</div>
		
		<a href="javascript:;" onclick="goto_attr_add()">添加分类信息</a>
	
	
	<script type="text/javascript">
		$(function(){
			//读取一级分类js数据
			$.getJSON("js/json/class_1.js",function(data){
				$("#attr_publish_class_1_select").append("<option value='0'>请选择</option>");
				$("#attr_publish_class_2_select").append("<option value='0'>请选择</option>");
				$("#attr_publish_tm_select").append("<option value='0'>请选择</option>");
				$(data).each(function(i,json){
					$("#attr_publish_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
				});
			});
		});
		
		function attr_publish_select_class_2_by_class_1_id(class_1_id){
			// 获得被选中的一级分类id
			// var class_1_id = $("#attr_publish_class_1_select option:selected").val();
			
			// 根据一级分类id加载二级分类和商品信息
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				$("#attr_publish_class_2_select").empty();
				$("#attr_publish_class_2_select").append("<option>请选择</option>")
				$(data).each(function(i,json){
					$("#attr_publish_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
				});
			});
			
			$.getJSON("js/json/trade_mark_"+class_1_id+".js",function(data){
				$("#attr_publish_tm_select").empty();
				$("#attr_publish_tm_select").append("<option>请选择</option>")
				$(data).each(function(i,json){
					$("#attr_publish_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
				});
			});
		}

		function attr_publish_get_attr_by_class_2_id(class_2_id){
			$.post("get_attr_by_class_2_id.do",{"class_2_id":class_2_id},function(data){
				$("#attr_publish_inner").html(data);
			})
		}
		
		function goto_attr_add(){
			var class_1_id = $("#attr_publish_class_1_select option:selected").val();
			var class_2_id = $("#attr_publish_class_2_select option:selected").val();
			var class_2_name = $("#attr_publish_class_2_select option:selected").html();
			if(class_1_id == 0){
				alert("请选择一级分类");
			}else if(class_2_id =="请选择"){
				alert("请选择二级分类");
			}else{
				window.open("goto_attr_add/"+class_2_id+"/"+class_2_name+".do");
			}
			
		}
	</script>
</body>
</html>