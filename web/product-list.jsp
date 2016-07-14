<%@ page import="sdkd.com.ec.model.EbPCategory" %>
<%@ page import="java.util.List" %>
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
	<div class="help"><a href="/shopping.servlet" class="shopping">购物车</a><c:if test="${user==null}"><a href="login.jsp">登录</a><a href="register.jsp">注册</a> </c:if>
		<c:if test="${user!=null}"><a href="guestbook.jsp">留言</a><a href="login.servlet?action=logout">退出</a></c:if></div>
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

	您现在的位置：<a href="/index.servlet">易买网</a> &gt; <a href="/pcontent.servlet?id_ep=${epcId}&action=detail_product"><c:if test="${epcParentId ==0}">${epcName}</c:if></a> &gt; <c:if test = "${epcParentId != 0}">${epcName}</c:if>

</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<c:forEach var ="ca" items = "${pcaList}">
					<c:if test="${ca.epcParentId ==0}">
						<dt>${ca.epcName}</dt>
					</c:if>
					<c:forEach var ="pca" items = "${pcaList}">
						<c:if test = "${ca.epcId == pca.epcParentId}">
							<dd><a href="/pcontent.servlet?id_ep=${pca.epcId}&action=detail_product">${pca.epcName}</a></dd>
						</c:if>
					</c:forEach>
				</c:forEach>
			</dl>
		</div>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<dt><img src="images/product/0_tiny.gif" /></dt>
				<dd><a href="product-view.jsp">法国德菲丝松露精品巧克力500g/盒</a></dd>
				<dt><img src="images/product/0_tiny.gif" /></dt>
				<dd><a href="product-view.jsp">法国德菲丝松露精品巧克力500g/盒</a></dd>
			</dl>
			<script type="text/javascript">
				document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
			</script>
		</div>
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>
			<div class="pager">

				<ul class="clearfix">
					<input type="text" size="5" id="toPage" /><button onclick="goPage()">GO</button>
					<select onselect="setPageSize()">
						<option value="4">==显示条数==</option>
						<option value="4">4</option>
						<option value="8">8</option>
						<option value="10">10</option>
					</select>
					<li><a href="/pcontent.servlet?action=detail_product&pageIndex=1&id_ep=${epcId}">首页</a></li>
					<c:if test="${pageIndex>1}"><li><a href="/pcontent.servlet?action=detail_product&pageIndex=${pageIndex-1}">上一页</a></li></c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<li><a href="/pcontent.servlet?action=detail_product&pageIndex=${page}id_ep=${epcId}">${page}</a></li>
					</c:forEach>
					<c:if test="${pageIndex<totalPage}"><li><a href="/pcontent.servlet?action=detail_product&pageIndex=${pageIndex+1}">下一页</a></li></c:if>
					<li><a href="/pcontent.servlet?action=detail_product&pageIndex=${totalPage}>末页</a></li>
				</ul>
			</div>
			<div class="clear"></div>

			<ul class="product clearfix">
				<c:forEach var = "proList" items="${proList}">
				<li>
					<dl>
							<dt><a href="/pcontent.servlet?id_pro=${proList.epId}" target="_blank"><img src="images/product/${proList.epId}.jpg" /></a></dt>
							<dd class="title"><a href="/pcontent.servlet?id_pro=${proList.epId}" target="_blank">${proList.epName}</a></dd>
							<dd class="price">${proList.epPrice}</dd>
					</dl>
				</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<input type="text" size="5" id="toPage" /><button onclick="goPage()">GO</button>
					<select onselect="setPageSize()">
						<option value="4">==显示条数==</option>
						<option value="4">4</option>
						<option value="8">8</option>
						<option value="10">10</option>
					</select>
					<li><a href="/pcontent.servlet?action=detail_product&pageIndex=1">首页</a></li>
					<c:if test="${pageIndex>1}"><li><a href="/pcontent.servlet?action=detail_product&pageIndex=${pageIndex-1}">上一页</a></li></c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<li><a href="/pcontent.servlet?action=detail_product&pageIndex=${page}">${page}</a></li>
					</c:forEach>
					<c:if test="${pageIndex<totalPage}"><li><a href="/pcontent.servlet?action=detail_product&pageIndex=${pageIndex+1}">下一页</a></li></c:if>
					<li><a href="/pcontent.servlet?action=detail_product&pageIndex=${totalPage}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
