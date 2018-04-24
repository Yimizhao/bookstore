<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/cart.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车信息</title>
</head>
<body>
	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">
	<h4>购物车信息</h4>
	您的购物车中有${sessionScope.shoppingCart.bookNumber }本书
	<table cellpadding="15">
		<tr>
			<th>书名</th>
			<th>数量</th>
			<th>价格</th>
		</tr>
		<c:if test="${!empty sessionScope.shoppingCart }">
			<c:forEach items="${sessionScope.shoppingCart.books }" var="shoppingCartItemEntry">
				<tr>
					<td>${shoppingCartItemEntry.value.book.title }</td>
					<td><input type="text" name="quantity"
						value="${shoppingCartItemEntry.value.quantity }" size="1"></td>
					<td>${shoppingCartItemEntry.value.book.price }</td>
					<td><a href="">删除</a></td>
				</tr>
			</c:forEach>
			<tr><td>总金额￥${sessionScope.shoppingCart.totalMoney }</td></tr>
		</c:if>
	</table>
	<a href="${pageContext.request.contextPath }/bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
	<a href="">清空购物车</a>
	<a href="">结账</a>

</body>
</html>