<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/book.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看图书详细信息</title>
</head>
<body>
	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">
	<h4>查看图书详细信息</h4>
	书名：${book.title }
	<br>
	<br> 作者：${book.author }
	<br>
	<br> 单价：${book.price }
	<br>
	<br> 出版时间：${book.publishingDate }
	<br>
	<br> 评论：${book.remark }
	<br>
	<br>
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>

</body>
</html>