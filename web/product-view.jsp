<%@ page import="java.util.List" %>
<%@ page import="sdkd.com.ec.model.EbProduct" %>
<%@ page import="sdkd.com.ec.model.EbPCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
	<%
		List<EbPCategory> pcalist = (List<EbPCategory>)request.getAttribute("pcaList");
	%>
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
	您现在的位置：<a href="/index.servlet">易买网</a> &gt; <a href="/pcontent.servlet?id_ep=${pcalist.epcId}&action=detail_product">${pcalist.epcName}</a> &gt; <c:if test = "${epcParentId != 0}">${epcName}</c:if>
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
	</div>
	<div id="product" class="main">
		<h1>${proName}</h1>
		<div class="infos">
			<div class="thumb"><img src="images/product/${epId}.jpg" /></div>
			<div class="buy">
				<p>商城价：<span class="price">${proPrice}</span></p>
                 <c:if test="${proStock==0}">
				 <p>库　存：无货</p>
				 </c:if>
				<c:if test="${proStock!=0}">
					<p>库　存：有货可拍</p>
				</c:if>
				<div class="button"><input type="button" name="button" value="" onclick="goBuy(${epId})" /><a href="#">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				${proDescription}<br />
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
