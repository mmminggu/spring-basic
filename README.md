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