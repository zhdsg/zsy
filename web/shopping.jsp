<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="#" class="shopping">购物车</a><c:if test="${user==null}"><a href="login.jsp">登录</a><a href="register.jsp">注册</a> </c:if>
		<c:if test="${user!=null}"><a href="guestbook.jsp">留言</a><a href="login.jsp">退出</a></c:if></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="/index.servlet">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="/shopping.servlet?action=pay" method = "post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var = "cart" items="${cart.items}" varStatus="status">
				<tr id="product_id_1">
					<td class="thumb"><img src="${cart.product.epFileName}" /><a href="/pcontent.servlet?id_pro=${cart.product.epId}">${cart.product.epName}</a></td>
					<td class="price" id="price_id_1">
						<span>￥${cart.cost}</span>
						<input type="hidden" value="${cart.cost}" />
					</td>
					<td class="number">
						<dl>
							<dt><input id="number_id_${cart.product.epId}" type="text" name="number" value="${cart.quantity}" /></dt>
							<dd onclick="modifyQuantity(${cart.product.epId},${status.index});">修改</dd>
						</dl>
					</td>
					<td class="delete"><a href="javascript:removeShopping(${cart.product.epId},${status.index});">删除</a></td>
				</tr>
				</c:forEach>
			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>>
	<
	<script type="text/javascript">
		document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
	</script>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
