<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 不支持EL --%>
<%--<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 支持EL--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- JSTL+EL 生成 HTML -->
<!-- 例如遍历list -->
<c:if test="${empty list}">
	list无记录
</c:if>
<c:if test="${not empty list}">
	<table>
		<tr>
			<th>标题</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.xxx }</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<!-- 遍历map 例如显示购物车 -->
<c:if test="${not empty map}">
	<table>
		<tr>
			<th>标题</th>
		</tr>
		<c:forEach var="entry" items="${map}">
			<tr>
				<td>${entry.key } ${entry.value }</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>