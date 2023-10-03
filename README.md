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
  * 직접 스프링 Bean을 등록하는 방식 : appConfig.xml~~~~
  