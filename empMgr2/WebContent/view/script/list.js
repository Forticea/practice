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
			p.append("<li><a href='list?pageNo=" + pagination.prev + "'>이전으로</a></li>");
		for (var i = pagination.startPage; i <= pagination.endPage; i++)
			p.append("<li><a href='list?pageNo=" + i + "'>" + i + "</a></li>");
		if (pagination.next > -1)
			p.append("<li><a href='list?pageNo=" + pagination.next + "'>다음으로</a></li>");
	});