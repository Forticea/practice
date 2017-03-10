<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	var result = <%= request.getAttribute("result") %>;
	$(function(){
		var target = $("#content tbody");
		var list = result.list;
		var pagination = result.pagination;
		$.each(list,function(idx, emp){
			var str = "<tr><td>" + emp.employeeId + "</td>";
			str =
				str + "<td>" + (emp.firstName+" " + emp.lastName)+"</td>";
			str = str + "<td>" + emp.departmentName + "</td></tr>";
			target.append(str);
		});
		$("#pagination").append("<ul></ul>");
		var pt = $("#pagination ul");
		if(p.prev>-1)
			pt.append("<li><a href='list?pageNo=" + p.prev +"'>이전으로</a></li>");
			for(var i=p.startPage; i<=p.endPage; i++)
				pt.append("<li><a href='list?pageNo="+i+"'>"+i+"</a></li>");
		if(p.next>-1)
			pt.append("<li><a href='list?pageNo=" + p.next +"'>다음으로</a></li>");
	});
</script>
</head>
<body>
	<header></header>
	<nav></nav>
	<section id="content">
		<table>
			<thead><tr><th>사번</th><th>이름</th><th>부서</th></tr></thead>
			<tbody>
			</tbody>
		</table>
		<div id="pagination"></div>
		<article>
		</article>
	</section>
	<footer></footer>
</body>
</html>