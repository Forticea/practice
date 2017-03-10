package com.empMgr.controller;

import javax.servlet.http.*;

import com.empMgr.service.*;
import com.icia.di.*;

public class EmpMgrController {
	@RequestMapping(value="/employee/list", method="GET")
	public static ModelAndView list(HttpServletRequest req) {
		EmpMgrService service = (EmpMgrService)req.getServletContext().getAttribute("service");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readEmpList(req));
		mav.setView("/view/list.jsp");
		return mav;
	}
	@RequestMapping(value="/employee/list", method="AJAX")
	public static ModelAndView listAjax(HttpServletRequest req) {
		EmpMgrService service = (EmpMgrService)req.getServletContext().getAttribute("service");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readEmpList(req));
		return mav;
	}
	@RequestMapping(value="/employee/view", method="GET")
	public static ModelAndView view(HttpServletRequest req) {
		EmpMgrService service = (EmpMgrService)req.getServletContext().getAttribute("service");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readEmployee(req));
		mav.setView("/view/view.jsp");
		return mav;
	}
	@RequestMapping(value="/employee/view", method="AJAX")
	public static ModelAndView viewAjax(HttpServletRequest req) {
		EmpMgrService service = (EmpMgrService)req.getServletContext().getAttribute("service");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readEmployee(req));
		return mav;
	}
}
