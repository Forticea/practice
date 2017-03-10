package manager.icia.di;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.slf4j.*;

import ch.qos.logback.core.net.*;

public class AnnotationRunner {
	// 사용자가 선택한 명령, 실행할 메소드의 이름, 메소드의 요청방식(GET, POST, AJAX)를 저장할 맵
	private static ArrayList<HashMap<String, Object>> mapping = new ArrayList<HashMap<String, Object>>();
	private static Logger logger = LoggerFactory.getLogger(AnnotationRunner.class);
	
	// 프론트 컨트롤러에서 딱 한번 실행할 메소드로 모든 경로와 실행할 메소드의 집합을 만들어 저장
	public static void getRequestMapping(String path, String packageName) {
		String pName = packageName.replaceAll("[.]", "\\\\");
		// 패키지 이름의 .을 \로 변경. 식이 복잡한 이유는 정규표현식을 사용해야만 바꿀 수 있기 때문
		try {
			// 이클립스는 프로젝트/.metadata.... 로 시작하는 폴더를 만들어서 현재 프로그램을 실행한다
			// 아래 문장은 현재 실행 중인 폴더명에 페캐지 이름을 합쳐 컨트롤러가 실제로 존재하는 하드디스크 폴더 이름을 얻어온다
			Enumeration<URL> en = AnnotationRunner.class.getClassLoader().getResources(pName);
			while (en.hasMoreElements()) {
				URL url = en.nextElement();
				// 얻어진 경로를 파일명으로 변경
				// 자바에서는 폴더와 파일은 모두 파일이다. 다만 폴더는 다른 파일을 담을 수 있다는 차이가 있을 뿐이다
				File file = new File(url.toURI());
				// 현재 파일이(즉 폴더가) 가지고 있는 파일들의 리스트를 얻어온다
				File[] files = file.listFiles();
				for (File f : files) {
					// f가 파일인 경우. 즉 폴더가 아닌 경우
					if (f.isFile()) {
						// Class.forName으로 로딩하려면 com.icia.controller.BoardController같은 형식의 이름(FQN:Full Qualified Name)이 필요
						String controllerName = packageName + "." + f.getName().substring(0, f.getName
								().lastIndexOf("."));
						Class clz = Class.forName(controllerName);
						// 현재 클래스의 메소드 리스트를 얻어온다
						// 클래스를 담는 클래스 Class가 있는 것 처럼, 메소드를 담는 클래스 Method도 있다
						Method[] mz = clz.getDeclaredMethods();
						for (Method m : mz) {
							// 클래스에 있는 메소드 중에서 RequestMapping 어노테이션을 가진 메소드를 찾는다
							if (m.isAnnotationPresent(RequestMapping.class)) {
								// RequestMapping이 적용된 메소드에서 RequestMapping 정보를 꺼낸다
								// @RequestMapping(value="/employee/list", method="AJAX")가 rm에 담겼다면
								RequestMapping rm = m.getAnnotation(RequestMapping.class);
								HashMap<String, Object> map = new HashMap<>();
								// rm.value()로 "/employee/list"를 꺼내 저장
								map.put("uri", rm.value());
								// "/employee/list" 경로일 때 실행할 메소드를 저장
								map.put("name", m);
								// rm.method()로 "AJAX"를 얻어내 저장
								map.put("method", rm.method());
								logger.info("매핑 추가 : " + rm.value() + " " + m.getName() + " " +rm.method());
								mapping.add(map);
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을 수 없습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	private static ModelAndView invoke(String uri, HttpServletRequest req) {
		// 실행할 메소드 객체
		Method method = null;
		// 메소드를 실행한 다음 리턴할 격체
		Object returnObject = null;
		
		// 사용자의 요청 방식이 GET, POST, AJAX 중 무엇인지 찾는다
		String requestMethod = req.getMethod();
		if(req.getHeader("X-Requested-With")!=null && (req.getHeader("X-Requested-With").toLowerCase()=="xmlhttprequest" || 

req.getHeader("ORIGIN") != null))
			requestMethod="AJAX";
		// 사용자가 요청한 주소(@RequestMapping의 value에 해당)와 요청 방식을 아니까 메소드를 검색하자
		for(int i=0; i<mapping.size(); i++) {
			HashMap<String,Object> map = mapping.get(i);
			// 요청 경로와 요청 방식이 일치하는 메소드를 꺼낸다
			if(map.get("uri").equals(uri) && map.get("method").equals(requestMethod))
				method = (Method) map.get("name");
			else
				continue;
			logger.info("메소드 호출 : " + method.getName());
			try {
				// 메소드를 실행(invoke)한다
				// invoke의 첫 매개변수는 객체인데 메소드가 static인 경우는 생략 가능하다
				// 암튼 실행하고 결과값은 returnObject에 담는다
				
				returnObject = method.invoke(null, req);
				break;
			}  catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return (ModelAndView)returnObject;
	}
	public static ModelAndView execute(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf("/")+1); 	
		// 루트 경로 "/" 제거. /empMgr/employee/list일 경우 empMgr/employee/list로 
		uri = uri.substring(uri.indexOf("/"));
		// 변경된 문자열에서 첫 /부터 잘라낸다. empMgr/employee/list에서 /employee/list를 얻어낸다
		// 전체적으로 보면 uri에서 웹 어플리케이션의 이름을 제거해 사용자가 선택한 명령만 뽑아내는 과정
		return invoke(uri, req);
	}
}
