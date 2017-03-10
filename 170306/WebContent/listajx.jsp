<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
		function printList(result){
			var list = result.list;
			var p = result.pagination;
			var t = $("#context-main tbody");
			$.each(list, function(idx, emp) {
				var str = "<tr><td>";
				str = str + emp.employeeId + "</td><td>"
				str = str + (emp.firstName + " " + emp.lastName) + "</td><td>"
				str = str + emp.departmentName + "</td><tr>";
				t.append(str);
			});
			$("#pagination").append("<ul></ul>");
			var pt = $("#pagination ul");
			if(p.prev-1)
				pt.append("<li><a href='list?pageNo="+p.prev+"'>이전으로</a></li>");
				for(var i=p.startPage; i<=p.endPage; i++)
					pt.append("<li><a href='list?pageNo'"+i+">"+i+"</a></li>");
			if(p.next-1)
				pt.append("<li><a href='list?pageNo="+p.next+"'>다음으로</a></li>");
		}
		$.ajax({
			type: "POST",
			url: "/170306/employee/list",
			data: {pageNo:1},
			success: function(result) {
				printList(result);
			}
		});
	});
</script>
</head>
<body>
	<!-- 문서의 구조를 나타내는 태그 : semantic 태그(div를 대체) -->
	<header></header>
	<nav></nav>
	<aside></aside>
	<section id = "context-main">
		<table>
			<tbody>
			</tbody>
		</table>
		<div id = "pagination"></div>
	</section>
	<footer></footer>
</body>
</html>