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
<title>商城检索页</title>
</head>
<body>
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