package manager.icia.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import manager.icia.dao.*;
import manager.icia.service.*;
import manager.icia.di.*;

@WebServlet({"/employee/*", "/account/*"})
public class DispatcherServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
		
		// 필요한 객체를 생성한다 -> 의존성 주입(Dependency Injection)
		ManagerDao dao = new ManagerDao();
		ManagerService service = new ManagerService(dao);
		ServletContext context = getServletContext();
		context.setAttribute("service", service);
		
		// di 패키지를 사용하려면 web.xml에 컨트롤러들의 패키지를 지정해야 한다
		// web.xml에 전역변수로 이름은 packageName, 값은 manager.icia.controller
		// 그걸 읽어와서 AnnotationRunner에 넘겨준다
		String path = getServletContext().getRealPath("/");
		String packageName = getServletContext().getInitParameter("packageName");
		AnnotationRunner.getRequestMapping(path, packageName);
		System.out.println("init");
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = AnnotationRunner.execute(request);
		if(mav.getView()==null) {
			HashMap<String,Object> model = mav.getModel();
			response.setContentType("application/x-json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(model.get("result"));
			out.print(model.get("result"));
			out.flush();
		}
		else {
			if (mav.isRedirect() == false) {
				HashMap<String,Object> model = mav.getModel();
				Set<String> set = model.keySet();
				for(String name:set)
					request.setAttribute(name, model.get(name));
				RequestDispatcher rd = request.getRequestDispatcher(mav.getView());
				rd.forward(request, response);
			} else {
				String destination = mav.getView();
				if(mav.getView().equals("/")) 
					destination = "/" + request.getServletContext().getInitParameter("webappName");
				System.out.println(destination);
				response.sendRedirect(destination);
			}
		}	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
