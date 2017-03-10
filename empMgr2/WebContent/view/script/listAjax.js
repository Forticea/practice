	$(function() {
		function printList(result) {
			var list = result.list;
			var pagination = result.pagination;
			var target = $("#content-main table tbody");
			$.each(list, function(idx, emp) {
				target.append("<tr><td>"+emp.employeeId+"</td><td>"+(emp.firstName+" "+emp.lastName) + "</td><td>" + emp.departmentName + "</td><td></tr>");
			});
			$("#pagination").append("<ul></ul>");
			var p = $("#pagination ul");
			if (pagination.prev > -1)
				p.append("<li><a href='list?pageNo=" + pagination.prev + "'>이전으로</a></li>");
			for (var i = pagination.startPage; i <= pagination.endPage; i++)
				p.append("<li><a href='list?pageNo=" + i + "'>" + i + "</a></li>");
			if (pagination.next > -1)
				p.append("<li><a href='list?pageNo=" + pagination.next + "'>다음으로</a></li>");
		}
		if(pageNo==null)
			pageNo = 1;
		$.ajax({
			type : "post",
			url : "/empMgr/employee/list",
			data: {"pageNo": pageNo},
			success : function(result) {
				printList(result);
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
	});