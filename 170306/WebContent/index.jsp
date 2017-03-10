<%@page import="manager.icia.dao.ManagerDao"%>
<%@page import="manager.icia.service.ManagerService"%>
<%@page import = "static org.mockito.BDDMockito.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpServletRequest req = mock(HttpServletRequest.class);
	given(req.getParameter("pageNo")).willReturn("2");
	ManagerDao dao = new ManagerDao();
	ManagerService service = new ManagerService(dao);
	String map = service.readList(req);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	var result = <%= map%>;
	$(function() {
		var list = result.list;
		var pagination = result.pagination;
		var target = $("#content-main table tbody");
		$.each(list, function(idx, emp) {
			var str = "<tr><td>"+emp.employeeId+"</td>";
			str = str + "<td><a href='view?pageNo=" + pagination.pageNo + "&employee_id=" + emp.employeeId + "'>" + (emp.firstName+" "+emp.lastName) + "</a></td>";
			str = str + "<td>" + emp.departmentName + "</td><td></tr>";
			target.append(str);
		});
		$("#pagination").append("<ul></ul>");
		var p = $("#pagination ul");
		if (pagination.prev > -1)
			p.append("<li><a href='list?pageNo=" + pagination.prev + "'>이전으로ㅎㅎ</a></li>");
		for (var i = pagination.startPage; i <= pagination.endPage; i++)
			p.append("<li><a href='list?pageNo=" + i + "'>" + i + "</a></li>");
		if (pagination.next > -1)
			p.append("<li><a href='list?pageNo=" + pagination.next + "'>다음으로</a></li>");
	});
</script>
</head>
<body>
	<header id="header">

	</header>
	<div id="content">
		<section id="content-main">
			<table>
				<tbody>
				</tbody>
			</table>
		</section>
		<div id="pagination">
		</div>
		<aside>
			
		</aside>
	</div>
	<footer>
		
	</footer>
</body>
</html>
