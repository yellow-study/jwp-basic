#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
- ContextLoaderListener
    - contextInitialized()
      DB정보 셋업 및 connection을 얻는다.
- DispatcherServlet
    - init()
        RequestMapping 생성 후, url에 따른 Controller를 맵핑한다. (innitMapping)
   - service()
     URL에 맞는 controller 호출한다.
     해당 controller로 부터 model과 view를 얻어 렌더링한다.
     

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
- DispatcherServlet
    - service()
        "/"에 맵핑되어있는 HomeController를 얻는다.
        HomeController로부터 ModelAndView 객체를 return 받는다.
        해당 객체는 첫 화면인 home.jsp와 question 리스트를 갖고 있다.
        question 리스트(model)는 view의 attribute로 set해주고 viewName을 참조하여 해당되는 view를 그린다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
두 개 이상의 쓰레드가 ShowController에 접근한다고 가정했을 때, 각각의 쓰레드는 서로 다른 questionId를 파라미터로 갖고 있을 수 있다.
따라서, questionId에 의해 결정되어지는 question과 answers는 쓰레드간 공유되지 않아야 한다.
현재는 전역변수이므로 쓰레드간 데이터가 공유되어질 수 있어 동기화 문제가 발생할 수 있다.
메서드 안의 변수는 각각 스레드의 스택영역에 저장되므로, question과 answers는 execute 메서드 안에서 정의되어야 한다.

    

