package com.empMgr.dao;

import java.sql.*;
import java.util.*;

import com.empMgr.util.*;
import com.empMgr.vo.*;

public class EmpMgrDao {
	public ArrayList<EmpList> selectByPaging(Connection conn, int startId, int endId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpList> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(Query.empList);
			pstmt.setInt(1, endId);
			pstmt.setInt(2, startId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpList empList = new EmpList();
				empList.setEmployeeId(rs.getInt("employee_id"));
				empList.setFirstName(rs.getString("first_name"));
				empList.setLastName(rs.getString("last_name"));
				empList.setDepartmentName(rs.getString("department_name"));
				list.add(empList);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt, rs);
		}
		return null;
	}
	public Employee selectById(Connection conn, int employeeId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee employee = null;
		try {
			pstmt = conn.prepareStatement(Query.employee);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return MappingUtil.getEmployeeFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt, rs);
		}
		return null;
	}
	public int selectCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			pstmt = conn.prepareStatement(Query.count);
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt, null);
		}
		return -1;		
	}
}
