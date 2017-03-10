package com.empMgr.test;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.servlet.http.*;

import org.junit.*;

import com.empMgr.dao.*;
import com.empMgr.service.*;

public class EmpServiceTest {
	private EmpMgrDao dao;
	private EmpMgrService service;
	@Before
	public void setup() {
		dao = new EmpMgrDao();
		service = new EmpMgrService(dao);
	}
	@Test
	public void listTest() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		given(req.getParameter("pageNo")).willReturn("2");
		String map = service.readEmpList(req);
		
		System.out.println(map);
	}
	
	
}
