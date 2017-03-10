package com.empMgr.service;

import java.sql.*;
import java.util.*;

import javax.servlet.http.*;

import com.empMgr.dao.*;
import com.empMgr.util.*;
import com.empMgr.vo.*;
import com.google.gson.*;

public class EmpMgrService {
	private EmpMgrDao dao;
	public EmpMgrService(EmpMgrDao dao) {
		this.dao = dao;
	}
	public String readEmpList(HttpServletRequest req) {
		Connection conn = JdbcUtil.getConnection();
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		int numberOfArticle = dao.selectCount(conn);
		Pagination pagination = PagingUtil.setPageMaker(pageNo, numberOfArticle);
		System.out.println(pagination);
		ArrayList<EmpList> list = dao.selectByPaging(conn, pagination.getStartArticle(), pagination.getEndArticle());
		HashMap<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("list", list);
		JdbcUtil.close(conn);
		return new Gson().toJson(map);
	}
	public String readEmployee(HttpServletRequest req) {
		Connection conn = JdbcUtil.getConnection();
		Employee employee = dao.selectById(conn, Integer.parseInt(req.getParameter("employee_id")));
		JdbcUtil.close(conn);
		return new Gson().toJson(employee);
	}
}
