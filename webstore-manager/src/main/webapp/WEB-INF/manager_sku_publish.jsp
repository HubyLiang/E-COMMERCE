<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN"><html>
<head>
<base href="<%=basePath %>" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SKU信息发布</title>
</head>
<body>

	<form action="sku_add.do" method="post"  enctype="multipart/form-data">
		<select name="flbh1" id="sku_publish_class_1_select" onchange="sku_publish_select_class_2_by_class_1_id(this.value)">
		</select>
		<br>
		
		<select name="flbh2" id="sku_publish_class_2_select" onchange="sku_publish_get_attr_by_class_2_id(this.value)">
		</select>
		<br>
		<select name="pp_id" id="sku_publish_tm_select" onchange="sku_publish_get_spu_by_class_id_and_tm_id()" >
		</select>
		<br>
		<select id="sku_publish_spu_select"></select>
		<br>
		
		<div id="sku_publish_inner"></div>
		
	</form>
	
	
	<script type="text/javascript">
		$(function(){
			//读取一级分类js数据
			$.getJSON("js/json/class_1.js",function(data){
				$("#sku_publish_class_1_select").append("<option value='0'>请选择</option>");
				$("#sku_publish_class_2_select").append("<option value='0'>请选择</option>");
				$("#sku_publish_tm_select").append("<option value='0'>请选择</option>");
				$("#sku_publish_spu_select").append("<option value='0'>请选择</option>");
				$(data).each(function(i,json){
					$("#sku_publish_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
				});
			});
			//加载到一级分类的下拉列表中
		});
		
		function sku_publish_select_class_2_by_class_1_id(class_1_id){
			
			// 根据一级分类id加载二级分类和商品信息
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				$("#sku_publish_class_2_select").empty();
				$("#sku_publish_class_2_select").append("<option>请选择</option>")
				$(data).each(function(i,json){
					$("#sku_publish_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
				});
			});
			
			$.getJSON("js/json/trade_mark_"+class_1_id+".js",function(data){
				$("#sku_publish_tm_select").empty();
				$("#sku_publish_tm_select").append("<option>请选择</option>")
				$(data).each(function(i,json){
					$("#sku_publish_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
				});
			});
		}
		
		function sku_publish_get_spu_by_class_id_and_tm_id(){
			var class_1_id = $("#sku_publish_class_1_select").val();
			var class_2_id = $("#sku_publish_class_2_select").val();
			var tm_id = $("#sku_publish_tm_select").val();
			
			$.post("get_spu_by_class_id_and_tm_id.do", {"class_1_id":class_1_id,"class_2_id":class_2_id,"tm_id":tm_id} ,function(data){
				$("#sku_publish_spu_select").empty();
				$(data).each(function(i,json){
					$("#sku_publish_spu_select").append("<option value="+json.id+">"+json.shp_mch+"</option>")
				})
			})
		}
		
		function sku_publish_get_attr_by_class_2_id(class_2_id){
			$.post("get_attr_by_class_2_id_sku.do",{"class_2_id":class_2_id},function(data){
				$("#sku_publish_inner").html(data);
			})
		}

	</script>
</body>
</html>
