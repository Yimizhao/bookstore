<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${!empty requestScope.errors }">
		<font color="red">${requestScope.errors }</font>
		<br>
		<br>
	</c:if>
	您一共买${sessionScope.shoppingCart.bookNumber }了本书
	<br>
	<br>
	您应付金额￥${sessionScope.shoppingCart.totalMoney }
	<br>
	<br>
	<form action="${pageContext.request.contextPath }/bookServlet?method=cash" method="post">
		信用卡姓名：<input type="text" name="creditCardName" value="${param.creditCardName }"/>
		<br>
		<br>
		信用卡账号:<input type="text" name="creditCardNumber" value="${param.creditCardNumber }"/> 
		<br>
		<br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>