package manager.icia.service;

import java.sql.*;
import java.util.*;

import javax.servlet.http.*;

import manager.icia.dao.*;
import manager.icia.util.*;
import manager.icia.vo.*;
import com.google.gson.*;

public class ManagerService {
	private ManagerDao dao;
	public ManagerService(ManagerDao dao) {
		this.dao = dao;
	}
	public String readList(HttpServletRequest req) {
		Connection conn = JdbcUtil.getConnection();
		int pageNo = 1;
		if(req.getParameter("pageNo")!=null)
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		int cntOfArticle = dao.selectCount(conn);
		Pagination p = PagingUtil.getPagination(pageNo, cntOfArticle);
		ArrayList<EmpList> list = dao.selectEmpList(conn, p.getStartArticle(), p.getEndArticle());
		// 서비스가 뷰에 넘길 데이터가 2개 : list는 게시판 글, pagination
		HashMap<String, Object> map = new HashMap<>();
		map.put("pagination", p);
		map.put("list", list);
		JdbcUtil.close(conn);
		return new Gson().toJson(map);
	}
	public String readEmployee(HttpServletRequest req) {
		Connection conn = JdbcUtil.getConnection();
		Employee employee = dao.selectEmployee(conn, Integer.parseInt(req.getParameter("employee_id")));
		JdbcUtil.close(conn);
		return new Gson().toJson(employee);
	}
}
