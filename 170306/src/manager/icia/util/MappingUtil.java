package manager.icia.util;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.http.*;

import manager.icia.vo.*;

public class MappingUtil {
	public static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setEmployeeId(rs.getInt("employee_id"));
		e.setFirstName(rs.getString("first_name"));
		e.setLastName(rs.getString("last_name"));
		e.setManagerId(rs.getInt("manager_id"));
		e.setManagerFirstName(rs.getString("manager_first"));
		e.setManagerLastName(rs.getString("manager_last"));
		e.setEmail(rs.getString("email"));
		e.setPhoneNumber(rs.getString("phone_number"));
		e.setHireDate(rs.getDate("hire_date"));
		e.setSalary(rs.getInt("salary"));
		e.setCommissionPct(rs.getDouble("commission_pct"));
		e.setJobTitle(rs.getString("job_title"));
		e.setDepartmentName(rs.getString("department_name"));
		e.setCity(rs.getString("city"));
		return e;
	}
}










