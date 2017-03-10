<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>ICIA 홈페이지입니다</title>
<link rel="stylesheet" href="/empMgr/view/css/header.css">
<link rel="stylesheet" href="/empMgr/view/css/main.css">
<link rel="stylesheet" href="/empMgr/view/css/footer.css">
<script>
	var result = <%=request.getAttribute("result")%>;
</script>
<script src="/empMgr/view/script/list.js"></script>
</head>
<body>
	<header id="header">
		<%@include file="/view/include/header.jsp"%>
	</header>
	<div id="content">
		<header id="content-header">
			<h1>섹션 제목</h1>
			<a href="#">더 보기</a>
		</header>
		<section id="content-main">
			<table>
				<thead>
					<tr>
						<th>사번</th>
						<th>이름</th>
						<th>부서</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="pagination"></div>
		</section>
		<aside id="content-aside">
			<%@include file="/view/include/aside.jsp"%>
		</aside>
		<div id="pagination"></div>
	</div>
	<footer>
		<%@include file="/view/include/footer.jsp"%>
	</footer>
</body>
</html>