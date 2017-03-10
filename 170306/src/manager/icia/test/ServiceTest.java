package manager.icia.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import manager.icia.dao.ManagerDao;
import manager.icia.util.JdbcUtil;
import manager.icia.util.PagingUtil;
import manager.icia.vo.EmpList;
import manager.icia.vo.Employee;
import manager.icia.vo.Pagination;

public class ServiceTest {
	private ManagerDao dao;
	public void ManagerService(ManagerDao dao) {
		this.dao = dao; // 주입. injection
	}
	public String readList(HttpServletRequest req) {
		Connection conn = JdbcUtil.getConnection();
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		int cntOfArticle = dao.selectCount(conn);
		Pagination p = PagingUtil.getPagination(pageNo, cntOfArticle);
		ArrayList<EmpList> list =
		dao.selectEmpList(conn, p.getStartArticle(), p.getEndArticle());
		// 서비스가 뷰에 넘길 데이터가 2개 : list는 게시판 글, pagination
		HashMap<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pagination", p);
		JdbcUtil.close(conn);
		return new Gson().toJson(map);
	}
	public String readEmployee(HttpServletRequest req) { 
		Connection conn = JdbcUtil.getConnection();
		int employeeId = Integer.parseInt(req.getParameter("employee_id"));
		Employee e = dao.selectEmployee(conn, employeeId);
		JdbcUtil.close(conn);
		return new Gson().toJson(e);
	}
}
























