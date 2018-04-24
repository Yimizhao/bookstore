<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看图书信息</title>
</head>
<body>

	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">
	<h4>查看图书信息</h4>
	<c:if test="${!empty param.title }">
		您已经将${param.title }加入到购物车中
		<br><br>
	</c:if>
	<c:if test="${!empty sessionScope.shoppingCart }">
	您的购物车中有${sessionScope.shoppingCart.bookNumber }件商品,<a href="${pageContext.request.contextPath }/bookServlet?method=toCart&pageNo=${bookPage.pageNo }">查看购物车</a>
		<br>
		<br>
	</c:if>
	<form
		action="<%=request.getContextPath()%>/bookServlet?method=getBooks"
		method="post">
		Price:<input type="text" name="minPrice" size="1"
			value="${sessionScope.minPrice }"> - <input type="text"
			name="maxPrice" value="${sessionScope.maxPrice }" size="1"> <br>
		<input type="submit" value="Submit">
	</form>
	<br>
	<table cellspacing="10">
		<c:forEach items="${bookPage.list }" var="book">
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/bookServlet?method=getBookInfo&bookId=${book.id }&pageNo=${bookPage.pageNo }">
						${book.title }</a> <br> ${book.author }</td>
				<td>${book.price }</td>
				<td><a
					href="<%=request.getContextPath()%>/bookServlet?method=addToCart&pageNo=${bookPage.pageNo }&bookId=${book.id }&title=${book.title }">加入购物车</a></td>
			</tr>
		</c:forEach>
	</table>

	<input type="hidden" id="totalPageNumber"
		value="${bookPage.totalPageNumber }">
	<br> 共${bookPage.totalPageNumber }页 当前第${bookPage.pageNo }页
	<c:if test="${bookPage.hasPrev }">
		<a
			href="<%=request.getContextPath()%>/bookServlet?method=getBooks&pageNo=1">首页</a>
		<a
			href="<%=request.getContextPath() %>/bookServlet?method=getBooks&pageNo=${bookPage.prevPage}">上一页</a>
	</c:if>
	<c:if test="${bookPage.hasNext }">
		<a
			href="<%=request.getContextPath() %>/bookServlet?method=getBooks&pageNo=${bookPage.nextPage}">下一页</a>
		<a
			href="<%=request.getContextPath()%>/bookServlet?method=getBooks&pageNo=${bookPage.totalPageNumber}">尾页</a>
	</c:if>
	转到
	<input type="text" id="pageNo" size="1">页
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/script/books.js"></script>
</body>
</html>