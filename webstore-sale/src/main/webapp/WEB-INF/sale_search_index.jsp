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
<link rel="icon" href="//www.jd.com/favicon.ico" mce_href="//www.jd.com/favicon.ico" type="image/x-icon">
<title>商城检索页</title>
</head>
<body>

	<div class="top">
		<div class="top_text">	
			<c:if test="${not empty user }">
				<a href="javascript:;">欢迎登陆,${user.yh_nch }</a>  <a href="goto_logout.do" target="_blank">登出</a>
			</c:if>
			<c:if test="${empty user }">
				<a href="goto_regist.do" target="_blank">注册 </a>  <a href="goto_login.do" target="_blank">登陆</a>
			</c:if>
		</div>
	</div>
	
	<div class="top_img">
		<img src="./images/top_img.jpg" alt="">
	</div>
	
	<div class="search">
		<div class="logo"><img src="./images/jd.png" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		<jsp:include page="sale_mini_shoppingCar.jsp"></jsp:include>
	</div>

	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					<a id="all_show" class="left_nav"  href="javascript:spu_publish_select_class_1();" >全部商品分类</a>
					<div class="nav_mini">
						<ul  id="index_class_1_li">
							<li>
								<div class="two_nav" style="display: none;" id="index_class_2_li">
								
								</div>
							</li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	

	<div id="search_index_up">
	
	</div>
	<input id="search_index_order" type="hidden" name="order" value="order by jg"/>
	${class_2_id} ${class_2_name}<br>
	<hr>
	商标:
	<c:forEach items="${list_tm }" var="tm">
		${tm.ppmch }
	</c:forEach>
	
	<hr>
	<c:forEach items="${list_object_attr }" var="object_attr">
		<div id="search_index_attr_list_${object_attr.id }">
			${object_attr.shxm_mch }: 
			<c:forEach items="${object_attr.list_value }" var="val">
				<a href="javascript:search_index_show_attr_value_up('${val.shxzh }${val.shxzh_mch }',${object_attr.id },${val.id });"> ${val.shxzh }${val.shxzh_mch }</a>
			</c:forEach>
			<br>
		</div>
	</c:forEach>
	<hr>
	
	<a href="javascript:search_index_order('order by jg');">价格</a>  
	<a href="javascript:search_index_order('order by sku_xl');">销量</a>
	<a href="javascript:search_index_order('order by sku.chjshj');">上架时间</a>  
	<a href="javascript:search_index_order('order by jg');">评论数量</a>
	
	<hr>
	<div id = "search_index_inner" >
		<jsp:include page="sale_search_index_inner.jsp"></jsp:include>
	</div>

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	
		function spu_publish_select_class_1(){
			
			$("#index_class_1_li").empty();
			//读取一级分类js数据
			$.getJSON("js/json/class_1.js",function(data){
				$(data).each(function(i,json){
					$("#index_class_1_li").append("<li value="+json.id+"  onmouseover='spu_publish_select_class_2_by_class_1_id(this.value)'><a href='javascript:;'>"+json.flmch1+"</a></li>");
				});
			});
			//加载到一级分类的下拉列表中
		}
		
		function spu_publish_select_class_2_by_class_1_id(class_1_id){
			// 获得被选中的一级分类id
			// var class_1_id = $("#index_class_1_li option:selected").val();
			
			// 根据一级分类id加载二级分类和商品信息
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				
				$("#index_class_2_li").empty();
				$(data).each(function(i,json){
					$("#index_class_2_li").append("<a href='goto_search_index/"+json.id+"/"+json.flmch2+".do' target='_blank'>"+json.flmch2+"</a>");
				});
				$("#index_class_2_li").show();
			});
		}
	
		function search_index_get_sku_by_attr_value_and_order_tm(){
			var class_2_id = ${class_2_id};
			var order = $("#search_index_order").val();
			
			var param = "";
			$("#search_index_up input").each(function(i,json){
				var pjson = $.parseJSON(json.value);
				console.log(pjson);
				param = param + "list_attr_value["+i+"].shxm_id="+pjson.shxm_id+"&list_attr_value["+i+"].shxzh_id="+pjson.shxzh_id+"&";
			})
			
			//查询字符串:list_attr_value[0].shxm_id=0&list_attr_value[0].shxzh_id=1&list_attr_value[1].shxm_id=2&list_attr_value[1].shxzh_id=2
			param = param + "class_2_id=" + class_2_id + "&order=" + order;
			console.log(param);
			
			$.ajax({
				type	:	"POST",
				url		:	"search_index_attr_value.do",
				data	:	param,
				success	:	function(result){
					$("#search_index_inner").html(result);
				}
			})
		}
		
		function search_index_show_attr_value_up(shxzh_shxzh_mch, attr_id, value_id){
			//将被选中的分类属性和属性值id,存储到一个特定区域,在上面显示被选中的分类属性和属性值名.
			console.log(shxzh_shxzh_mch);
			var a = "<div id='search_index_up_inner_"+attr_id+"'>";
			var b = "<a href='javascript:search_index_show_attr_value_down("+attr_id+");'>"+shxzh_shxzh_mch+"</a>";
			var c = "<input type='hidden' value='{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+value_id+"}' />";
			var d = "</div>"
			$("#search_index_up").append(a+b+c+d);
			/* $("#search_index_up").append("<input type='text' value='"+attr_id+"_"+value_id+"'/>"); */
			
			$("#search_index_attr_list_"+attr_id).hide();
			search_index_get_sku_by_attr_value_and_order_tm();
		}
		
		function search_index_show_attr_value_down(attr_id){
			
			$("#search_index_up_inner_"+attr_id).remove();
			$("#search_index_attr_list_"+attr_id).show();
			search_index_get_sku_by_attr_value_and_order_tm();
		}
		
		function search_index_order(order){
			var old_order = $("#search_index_order").val();
			if(old_order == order){
				$("#search_index_order").val(order+" desc ");
			}else{
				$("#search_index_order").val(order);
			}
			search_index_get_sku_by_attr_value_and_order_tm();
		}
	</script>
</body>
</html>