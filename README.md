<h2>SpringBoot Basic</h2>

* 스트링부트 기본<br>
  https://www.inflearn.com/course/스프링-핵심-원리-기본편

---

<h2>Notes</h2>
<b>2023.09.28 (section1)</b>

* 스프링 프레임워크 : EJB를 대체하는 Java 기반 애플리케이션 개발을 지원하는 오픈소스
* 스프링 부트 : 스프링 프레임워크를 편리하게 사용할 수 있게 도와주는 도구
* 객체지향의 다형성
  * 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경
  * 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경
  * 예시) 운전자 - 자동차, 공연 무대, 키보드와 마우스
  

* SOLID : 클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙
  * SRP: 단일 책임 원칙(single responsibility principle)
    * 책임의 기준은 변경
    * 예) UI 변경, 객체의 생성과 사용을 분리
  * OCP: 개방-폐쇄 원칙 (Open/closed principle)
    *  확장에는 열려 있으나 변경에는 닫혀있어야 함
  * LSP: 리스코프 치환 원칙 (Liskov substitution principle)
    * 예) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반
  * ISP: 인터페이스 분리 원칙 (Interface segregation principle)
    * 예) 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
  * DIP: 의존관계 역전 원칙 (Dependency inversion principle)
    * 인터페이스에만 의존해야 함

<br>
<b>2023.09.29 (section2)</b>

* 회원 주문 예제 - 비지니스 요구사항과 설계
  * 회원
    * 회원 가입, 조회 가능
    * 회원 등급 : 일반, VIP
    * 회원 데이터 : 자체 DB 구축 또는 외부 시스템과 연동 (미확정)

  * 주문과 할인 정책
    * 회원은 상품 주문 가능
    * 회원 등급에 따라 할인 정책 적용
    * 할인 정책 : 정액할인(VIP만 1000원 할인) 또는 정률할인(퍼센테이지 할인)


<br>
<b>2023.10.01 ~ (section3)</b>

* AppConfig를 사용하여 클라이언트인 OrderServiceImpl, MemberServiceImpl에 구현 객체를 대신 생성하고 주입

  
* **제어의 역전 IoC (Inversion of Control)**
  * 프로그램의 제어 프름을 직접 제어하는 것이 아니라 외부에서 관리하는 것 ( ex) AppConfig가 직접 OrderServiceImpl 생성 )


* 프레임워크와 라이브러리
  * 프레임워크 : 내가 작성한 코드를 제어하고 대신 실행하는 것 (JUnit)
  * 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당하는 것
  

* **의존관계 주입 DI (Dependency Injection)**
  * 정적인 클래스 의존관계
    * 클래스가 사용하는 import 코드만 보고 의존관계 판단, 애플리케이션 실행하지 않고 분석
    * 클래스 의존관계 만으로는 실제 어떤 객체가 OrderServiceImpl에 주입되는지는 알 수 없음
  * 동적인 객체 인스턴스 의존관계
    * 애플리케이션 실행 시점(런타임)에 실제 구현 객체를 생성하고 의존관계가 연결되는 것
    * 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결됨
    * 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있음
  * IoC 컨테이너, <b>DI 컨테이너</b>
    * 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것
    * ex) AppConfig
  * 스프링 컨테이너
    * ApplicationContext : 스프링 컨테이너
    * @Configuration : 구성 정보로 사용
    * @Bean : 메서드를 모두 호출 해서 반환된 객체를 스프링 컨테이너에 등록  ==> <b>스프링 빈</b>
      * 메서드의 이름을 스프링 빈의 이름으로 사용


<br>
<b>2023.10.03 (section4)</b>

* **스프링 컨테이너**
  * 스프링 컨테이너 생성 과정
    1) 스프링 컨테이너 생성
       * new AnnotationConfigApplicationContext(AppConfig.class)
       * AppConfig.class를 구성 정보로 지정
    2) 스프링 빈 등록
       * 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈 등록
       * 스프링 빈 저장소에 <b>빈 이름 - 빈 객체</b>로 등록
       * 빈 이름은 메서드 이름을 사용, 또는 @Bean(name="memberService2")로 직접 지정 가능
    3) 스프링 빈 의존관계 설정
  * 스프링 빈 정보 출력
    * ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름 조회
    * ac.get() : 빈 이름으로 빈 객체(인스턴스)를 조회
    * Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
    * Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
    

* **BeanFactory**
  * 스프링 컨테이너의 최상위 인터페이스
  * 스프링 빈을 관리하고 죄회하는 역할
  * getBean() 제공


* **ApplicationContext**
  * BeanFactory 기능을 모두 상속 받아서 제공
  * 다양한 부가기능 제공
    * 메시지소스를 활용한 국제화 기능 : 한국 - 한국어 / 영어권 - 영어
    * 환경변수 : 로컬, 개발, 운영등을 구분해서 처리
    * 애플리케이션 이벤트 : 이벤트를 발생하고 구독하는 모델을 편리하게 지원
    * 편리한 리소스 조회 : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

  
* **스프링 빈 메타 정보 - BeanDefinition**
  * BeanDefinition이라는 추상화로 다양한 설정 형식을 지원할 수 있음
    <br> ==> BeanDefinition 자체가 인터페이스
  * 역할과 구현을 개념적으로 나눔
  * 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 BeanDefinition만 알면 됨
  * 메타정보(BeanDefinition)를 기반으로 스프링 빈 생성
  * BeanDefinition 생성 순서
    1) AnnotationConfigApplicationContext가 AnnotatedBeanDefinitionReader를 사용하여 AppConfig.class를 읽음
    2) **BeanDefinition** 생성
  * 팩토리 메소드를 통해서 등록하는 방식 : Appconfig.class
  * 직접 스프링 Bean을 등록하는 방식 : appConfig.xml

<br>
<b>2023.10.04 ~ (section5)</b>

* **싱글톤 컨테이너**
  * 웹어플리케이션과 싱글톤
    * 웹어플리케이션은 여러 요청이 동시에 들어옴
      <br> --> 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청 할 때마다 객체를 새로 생성해서 **메모리 낭비가 심함**
    * 객체가 1개만 생성 되도록 하고 공유하여 사용하면 해결 => **싱글톤 패턴**
  * 싱글톤 패턴
    * 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
    * 객체 인스턴스를 2개 이상 생성하지 못하도록 막음
      * private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하게 막음
    * 단점 : 코드가 많음, DIP 위반(구체클래스에 의존), 유연성이 떨어짐
  * 싱글톤 컨테이너
    * 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리
    * 스프링 컨테이너는 싱글톤 컨테이너의 역할을 함
      * 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리
  * 싱글톤 방식의 주의점
    * 싱글톤 객체는 무상태(stateless)로 설계해야 함
      * 특정 클라이언트에 의존적인 필드가 있으면 안됨
      * 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안됨
      * 가급적 읽기만 가능해야 함 (값이 수정되지 않아야 함)
      * 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터 ThreadLocal등을 사용해야 함
    * 스프링 빈의 빌드에 공유 값을 설정하면 안됨
  * @Configuration
    * 인스턴스 값 : class hello.core.AppConfig **$$EnhancerBySpringCGLIB$$d05c737**
    * 스프링 빈을 등록하는 과정 속에서 CGLIB라는 바이트 조작 라이브러리를 사용
    * AppConfig 클래스를 상속받은 임의의 다른 클래스를 생성 후 스프링 빈으로 등록
    * 임의의 다른 클래스가 싱글톤이 보장되도록 해줌
    * @Configuration을 붙이지 않고 @Bean만 하게 되도 스프링 빈으로 등록은 되지만, 조작되지 않은 클래스(AppConfig)로 등록 
      * 싱글톤이 보장 되지 않게 됨

<br>
<b>2023.10.07 (section6)</b>

* **컴포넌트 스캔**
  * @ComponentScan : @Component가 붙은 클래스를 스캔해서 스프링 빈으로 등록
  * @Autowired : 자동으로 의존관계 주입
    * ac.getBean(MemberRepository.class) -> 같은 타입의 빈을 찾아서 주입

    
* **탐색 위치**
  * basePackages : 탐색할 패키지 지정
  * basePackageClasses : 지정한 클래스의 패키지를 탐색
  * 디폴트 : @ComponentScna이 붙은 설정 정보의 클래스의 패키지를 탐색


* 필터
  * includeFilters : 컴포넌트 스캔 대상을 추가로 지정
  * excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정


* 중복 등록과 충돌
  * 자동 빈 등록 : @Component로 등록 / 수동 빈 등록 : AppConfig에 @Bean으로 직접 등록
  * 자동 빈 등록 시 이름이 같은 경우 ConflictingBeanDefinitionException 예외 발생
  * 자동 빈 등록, 수동 빈 등록에서 이름이 같은 경우 수동 빈 등록이 우선권을 가짐
    * 수동 빈이 자동 빈을 오버라이딩 함
  * **최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값 변경**
    * application.properties에 spring.main.allow-bean-definition-overriding=true 추가 하면 오버라이딩 사용


<br>
<b>2023.10.8 (section7)</b>

* 의존관계 자동 주입
  * 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작 (일반 객체에 @Autowired는 동작 안함)
  * 생성자 주입
    * 생성자 호출시점에 딱 1번만 호출되는 것이 보장
    * **불편, 필수** 의존관계에 사용
    * 생성자가 한 개 있을 때 @Autowired 생략 가능
  * 수정자 주입 (setter 주입)
    * **선택, 변경** 가능성이 있는 의존관계에 사용
  * 필드 주입 : 필드에 바로 @AutoWired 주입
  * 일반 메서드 주입 : 한번에 여러 필드를 주입 받을 수 있음


* 자동 주입 대상 옵션 처리
  * @Autowired(required=false) : 자동 주입 대상이 없으면 수정자 메서드 자체가 호출 안됨
  * @Nullable : 자동 주입 대상이 없으면 null이 입력
  * Optional<> : 자동 주입 대상이 없으면 Optional.empty가 입력


* 조회 대상 빈이 2개 이상일 때 해결 방법
  * @Autowired : 자동으로 의존관계 주입 시 같은 타입의 빈을 찾아서 주입 하는 데 조회 대상 빈이 2개일 경우 문제 발생
  1. 필드 명을 빈 이름으로 변경
     * ex) discountPolicy -> RateDiscountPolicy
  2. @Quilifier 사용 (추가 구분자)
     * @Qualifier 매칭 -> 빈 이름 매칭 -> 없으면 NoSuchBeanDefinitionException 발생 
     * ex) @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
  3. @Primary 사용
     * 우선순위로 지정되어 의존관계 주입
     * @Quilifier가 우선권이 높음

<br>
<b>2023.10.9 (section8)</b>

* **빈 생명주기 콜백**
  * 스프링 빈의 이벤트 라이프사이클
    * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    * 초기화 콜백 : 빈이 생성 된 후, 빈의 의존관계 주입이 완료되면 호출
    * 소멸전 콜백 : 빈이 소멸되기 직전에 콜백
  * 인터페이스 InitializingBean, DisposableBean (거의 사용 X)
  * 빈 등록 초기화, 소멸 메서드 : @Bean(initMethod = "init", destroyMethod = "close" -> destroyMethod는 생략 가능)
  * 애노테이션 @PostConstruct, @PreDestroy (**권장**)
  * 코드 수정이 불가능 한 외부 라이브러리를 초기화, 종료 시 @Bean(initMethod = "init", destroyMethod = "close") 사용


<br>
<b>2023.10.11 ~ (section9)</b>

* **빈 스코프** : 빈이 존재할 수 있는 범위
  * 싱글톤 : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
  * 프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 짧은 범위의 스코프
  * 웹 관련 스코프
    * request : 웹 요청이 들어오고 나갈 때 까지 유지되는 스코프
    * session : 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프
    * application : 웹의 서블릿 컨텍스와 같은 범위로 유지되는 스코프

  * 싱글톤 스코프 : 빈 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환
  * 프로토타입 스코프
    * **스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리** 
    * 항상 새로운 인스턴스를 생성해서 반환
    * 스프링 컨테이너는 반환 후 더이상 관리하지 않음
    * @PreDestory 같은 종료 메서드 호출되지 않음
    * 싱글톤 빈과 함께 사용 시 문제점
      * **싱글톤 빈은 생성 시점에만 의존관계를 주입** 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만 싱글톤 빈과 계속 유지됨
    * 싱글톤 빈과 함께 사용 시 문제점 해결
      * Dependency Lookup (DL) : 의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것
      * ObjectProvider : 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공
        <br> --> 스프링에 의존적
      * javax.inject.Provider : JSR-330 자바 표준 사용
        <br> --> 스프링에 의존적이지 않으나 별도의 라이브러리 필요

  * 웹 스코프
    * 웹 환경에서만 동작
    * 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리 -> 종료 메서드 호출
  
  * request 스코프
    * 스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, request 스코프 빈은 실제 고객의 요청이 와야 생성할 수 있음
    * ObjectProvider를 사용하면 ObjectProvider.getObject()를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있음
      <br> --> 같은 HTTP 요청이면 같은 스프링 빈이 반환됨
