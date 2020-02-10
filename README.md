#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
ContextLoaderListener 에 web application 초기화 과정이 시작될때 호출 되는 메소드인 contextInitialized 에서  
데이터베이스 초기화를 위해 jwp.sql의 스크립트를 추가 해주고 
ConnectionManager에서 DB 접속정보를 통해 데이터베이스와 connection을 맺는 일련의 과정을 수행합니다.  

중앙 집중식으로 FrontController의 역할을 할 DispatcherServlet이 초기화 될때
컨트롤러를 매핑하기 위해 ResquestMapping 객체를 생성한 후 초기화를 진행 합니다.
이때 initMapping은 요청 URL에 따라 수행할 작업의 핸들러(컨트롤러)가 Map형식으로 매핑 되어 있습니다.

Servlet은 최초 요청이 들어올때 초기화 되지만 DispatcherServlet은 Server가 start될때 초기화 시키기 위해 loadOnStartup 값을 1로 지정해 줍니다.


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
localhost:8080로 접근 시 먼저 Resource filter의 doFilter 를 통해 해당 요청이 정적 리소스의 요청인지 확인 한 후 
요청을 DispatcherServlet(/에 대한 요청을 받는)으로 위임한다.

이떄 DispatcherServlet의 service() 메소드에서 요청받은 URL과 매핑되어 있는 핸들러(Controller)를 RequestMapping을 통해 가져온 온 후 작업을 수행한다.
여기서는 HomeController의 excute() 메소드를 통해 작업을 수행하고 ModelAndView를 리턴한다. 
이렇게 리턴한 ModelAndView를 가지고 render() 메소드를 수행 한다. 
이 요청에서 사용되는 View 는 JspView 이며 전달 받은 model 데이터를 가지고 home.jsp에 전달하여 HTML을 생성한 후 응답 한다.


#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
