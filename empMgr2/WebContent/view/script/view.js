	$(function() {
		var str = "<tr><td>사번</td><td>" + result.employeeId + "</td></tr>"; 
		str = str + "<tr><td>이름</td><td>" + (result.firstName + " " + result.lastName) + "</td></tr>";
		str = str +  "<tr><td>관리자 번호</td><td>" + result.managerId + "</td></tr>";
		str = str +  "<tr><td>관리자 이름</td><td>" + (result.managerFirstName + " " + result.managerLastName) + "</td></tr>";
		str = str +  "<tr><td>이메일</td><td>" + result.email + "</td></tr>";
		str = str +  "<tr><td>전화번호</td><td>" + result.phoneNumber + "</td></tr>";
		str = str +  "<tr><td>입사일</td><td>" + result.hireDate + "</td></tr>";
		var sal = result.salary + (result.salary * result.commissionPct);
		str = str +  "<tr><td>급여</td><td>" + sal + "</td></tr>";
		str = str +  "<tr><td>직무</td><td>" + result.jobTitle + "</td></tr>";
		str = str +  "<tr><td>부서명</td><td>" + result.departmentName + "</td></tr>";
		str = str +  "<tr><td>근무지</td><td>" + result.city + "</td></tr>";
		$("#content-main tbody").append(str);
		var pagination = $("#pagination");
		pagination.append("<button id='update'>내용 수정</button>");
		pagination.append("<button id='list'>리스트로</button>");
	});