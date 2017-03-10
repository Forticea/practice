package com.empMgr.test;

import java.sql.*;
import java.util.*;

import org.junit.*;

import com.empMgr.dao.*;
import com.empMgr.util.*;
import com.empMgr.vo.*;

public class EmpDaoTest {
	private EmpMgrDao dao;
	@Before
	public void setup() {
		dao = new EmpMgrDao();
	}
	//@Test
	public void selectByPagingTest() {
		Connection conn = JdbcUtil.getConnection();
		ArrayList<EmpList> list = dao.selectByPaging(conn, 11, 20);
		System.out.println(list);
	}
	@Test
	public void selectByIdTest() {
		Connection conn = JdbcUtil.getConnection();
		Employee emp = dao.selectById(conn, 101);
		System.out.println(emp);
	}
}
