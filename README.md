#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* ContextLoaderListener 에 web application 초기화 과정이 시작될때 호출 되는 메소드인 contextInitialized 에서  
데이터베이스 초기화를 위해 jwp.sql의 스크립트를 추가 해주고 
ConnectionManager에서 DB 접속정보를 통해 데이터베이스와 connection을 맺는 일련의 과정을 수행합니다.  

중앙 집중식으로 FrontController의 역할을 할 DispatcherServlet이 초기화 될때
컨트롤러를 매핑하기 위해 ResquestMapping 객체를 생성한 후 초기화를 진행 합니다.
이때 initMapping은 요청 URL에 따라 수행할 작업의 핸들러(컨트롤러)가 Map형식으로 매핑 되어 있습니다.

Servlet은 최초 요청이 들어올때 초기화 되지만 DispatcherServlet은 Server가 start될때 초기화 시키기 위해 loadOnStartup 값을 1로 지정해 줍니다.


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
