package manager.icia.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import manager.icia.service.*;
import manager.icia.di.*;

public class ManagerController {
	// AnnotationRunner는 value, method, listAjax()란 메소드 이름
	@RequestMapping(value="/employee/list", method="AJAX")
	public static ModelAndView listajax(HttpServletRequest req) {
		// 서비스나 Dao는 여러개 만들 필요가 없다. 하나만 만들자
		// 1. 전부다 static 메소드
		// 2. singleton -> 생각보다 어렵다
		// 3. 프론트컨트롤러가 만들어서 저장해둔다
		ManagerService service = (ManagerService)req.getServletContext().getAttribute("service");
		// ServletContext ctx = req.getServletContext();
		// ManagerService service = (ManagerService)ctx.getAttribute("service");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readList(req));
		// AJAX는 결과를 출력하는 뷰가 존재하지 않는다.
		// 일반적인 jsp는 내용과 디자인이 합꼐 클라이언트로
		// AJAX는 데이터만 주고 받는다. 뷰는 클라이언트가 알아서...(예를 들어 마트앱 처음에 다운받을때 데이터 많이소모하고 실제 사용시에 데이터가 소모되지않는다.)
		return mav;
	}
	@RequestMapping(value="/employee/list", method="GET")
	public static ModelAndView list(HttpServletRequest req) {
		ServletContext ctx = req.getServletContext();
		ManagerService service = (ManagerService)ctx.getAttribute("service"); 
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.readList(req));
		mav.setView("/view/list.jsp");
		return mav;
	}
}
