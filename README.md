#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* ContextLoaderListener는 ServletContextListener의 구현체로 웹서버 초기화 및 종료될때의 동작을 구현할 수 있다.
* 웹서버가 초기화 될때 ContextLoaderListener의 contextInitialized가 수행되며 현재 코드에서는 h2db정보를 ConnectionManager에서 참조하여 셋업하고 초기화 하고 있다.
* 모든 요청은 DispatcherServlet으로 들어오게된다. (urlPatterns가 "/" 이므로)
* DispatcherServlet은 요청 url을 분석하고 매칭되는 Controller에게 작업을 위임하고 반환되는 ModelAndView객체를 통해 이후 작업(response)을 수행한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* http://localhost:8080 로 접근하게 되면 DispatcherServlet은 "/" 요청에 맵핑된 HomeController에게 작업을 위임한다.
* HomeController는 view로는 jspView를 반환하고 Model로는 질문리스트를 반환한다.
* DispatcherServlet은 HomeController가 반환한 ModelAndView객체의 View를 render한다.
* render하는 과정에서 인자로 넘겨받은 Model은 request attribute에 셋업되고 자신의 viewName을 참조하여 응답을 구성한다.
* 마지막으로 jsp에서 화면을 구성하고 넘겨받은 Model로 질문리스트를 구성한다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* RequestMapping은 Servlet init시에 한번만 생성되며 이후에는 모든 쓰레들이 RequestMapping에서 생성된 Controller인스턴스를 공유한다.
* 그런데 ShowController에서 클래스 멤버 변수로 Question객체와 List<Answer>객체를 가지고 있으면 이또한 쓰레드 Heap영역에 생성된 클래스의 멤버 변수를 공유하게 된다.
* 멀티쓰레드 상황에서 만약 쓰레드가 동시에 다른 questionId를 가지고 요청을 하게 되면 사용자는 정상적인 응답을 받을 수 없게된다.
* 예를들어 사용자A가 1번 질문을 사용자B가 2번 질문을 동시에 요청했을경우 A 혹은 B는 자신이 요청한 질문이 아닌 다른 질문에 대한 응답을 받을 수 있다.
* 이럴 경우 Question객체와 List<Answer>를 메서드 내부의 로컬변수로 선언할 경우 문제는 해결된다. 메서드 내의 로컬 변수에 대해서는 스레드마다 각각의 객체를 Heap영역에 생성해서 참조할 것이기 때문이다.
