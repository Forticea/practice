<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>ICIA 홈페이지입니다</title>
	<link rel="stylesheet" href="view/css/header.css">
	<link rel="stylesheet" href="view/css/main.css">
	<link rel="stylesheet" href="view/css/footer.css">
</head>
<body>
	<header id="header">
		<%@include file="/view/include/header.jsp" %>
	</header>
	<div id="content">
		<header id="content-header">
			<h1>섹션 제목</h1>
			<a href="#">더 보기</a>
		</header>
		<section id="content-main">
			여기는 루트 페이지입니다
		</section>
		<aside id="content-aside">
			<%@include file="/view/include/aside.jsp" %>
		</aside>
	</div>
	<footer>
		<%@include file="/view/include/footer.jsp" %>
	</footer>
</body>
</html>