package manager.icia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import manager.icia.util.JdbcUtil;
import manager.icia.util.MappingUtil;
import manager.icia.vo.EmpList;
import manager.icia.vo.Employee;

/*
	서비스에서 Connection을 얻어와서 Dao로 넘겨준다, 왜?
	예를들어 송금을 한다면 A계좌에서 돈이 빠져나가고 B계과에 돈이 추가 되어야 한다
	이런송금작업을 분리 할수있는가? 불가능
		즉 B좌에 추가하는데 실패한 경우 A계좌에서 빠져나간 것 까지 취소 ->transaction
		트랜잭션이 성공하면 commit, 실패하면 rollback
		commit이나 rollback은 Connection에서 하는 작업
		DAO는 작업이 실패한 경우 SQLExption을 서비스에 전달(트랜잭션 취소할 수 있게)	
 */
public class ManagerDao {
	// 1.잡리스트
	public ArrayList<HashMap<String, String>> selectJob(Connection conn){
		return null;	
	}
	// 2.부서리스트
	public ArrayList<HashMap<String, Object>> selectDepartment(Connection conn){
		return null;
	}
	// 3.매니저리스트
	public ArrayList<HashMap<String, Object>> selectManager(Connection conn){
		return null;	
	}
	// 4.사번 맥스+1
	public int selectMax(Connection conn){
		return 0;	
	}
	// 5.사번 count
	public int selectCount(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql.count);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt,null);
		}
		return 0;
	}
	// 6.페이징쿼리(사원리스트)
	public ArrayList<EmpList> selectEmpList(Connection conn, int startNum, int endNum){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpList> list = new ArrayList<>();
		try{
		pstmt = conn.prepareStatement(sql.empList);
		pstmt.setInt(1, startNum);
		pstmt.setInt(2, endNum);
		rs = pstmt.executeQuery();
		while(rs.next()){
			EmpList empList = new EmpList();
			empList.setEmployeeId(rs.getInt("employee_id"));
			empList.setFirstName(rs.getString("first_name"));
			empList.setLastName(rs.getString("last_name"));
			empList.setDepartmentName(rs.getString("department_name"));
			list.add(empList);
		}
		return list;
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt,rs);
		}
		return null;
	}
	// 7. employee
	public Employee selectEmployee(Connection conn, int employeeId){	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		pstmt = conn.prepareStatement(sql.employee);
		pstmt.setInt(1, employeeId);
		rs = pstmt.executeQuery();
			if(rs.next()){
				return MappingUtil.getEmployeeFromResultSet(rs);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt,rs);
		}
		return null;
	}
}
