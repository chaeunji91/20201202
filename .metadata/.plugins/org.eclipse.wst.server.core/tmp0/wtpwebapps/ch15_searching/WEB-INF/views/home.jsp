<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
	<ul>
		<c:forEach var="board" items="${listBoard}">
			<li> <!-- 호출할때 인자가 없으면 자동으로 만들어줌 ex. criteria -->
				<a href="/post/listPost?boardId=${board.id}">${board.name}</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>
