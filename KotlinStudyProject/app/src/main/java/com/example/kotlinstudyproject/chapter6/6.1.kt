package com.example.kotlinstudyproject.chapter6

/*
6.1 널 가능성
코드를 더 간결하고 읽기 쉽게 해주며 생산성을 높여주는 코틀린 특성
코틀린에서 가장 중요한 부분인 타입 시스템을 살펴보기
- 코드의 가독성을 향상시키는데 도움이 되는 몇 가지 특성을 새로 제공
- 널이 될 수 있는 타입
- 읽기 전용 컬렉션

널 가능성 : NullPointerException(NPE)를 피할 수 있게 돕기 위한 코틀린 타입 시스템의 특성
- 코틀린을 비롯한 최신 언어에서 null에 대한 접근 방법은 가능한 한 이 문제를 실행 시점에서 컴파일 시점으로 옮기는 것
- 컴파일러가 여러 가지 오류를 컴파일 시 미리 감지해서 실행 시점에 발생 할 수 있는 예외의 가능성을 줄일 수 있다

6.1.1 널이 될 수 있는 타입
- 코틀린 타입 시스템이 널이 될 수 있는 타입을 명시적으로 지원한다는 점
- 문자열을 인자로 하는 함수가 널과 문자열을 받을 수 있게 하려면 타입 이름 뒤에 물음표(?)를 명시해야 한다
- 그 타입의 변수나 프로퍼티에 null 참조를 저장할 수 있다는 뜻
- 널이 될 수 있는 타입의 변수가 있다면 그에 대해 수행할 수 있는 연산이 제한된다

6.1.2 타입의 의미
타입은 어떤 값들이 가능한지와 그 타입에 대해 수행할 수 있는 연산의 종류를 결정
코틀린의 널이 될 수 있는 타입은 종합적인 해법을 제공
널이 될 수 있는 타입과 널이 될 수 없는 타입을 구분하면
- 각 타입의 값에 대해 어떤 연산이 가능할지 명확히 이해할 수 있고,
- 실행 시점에 예외를 발생시킬 수 있는 연산을 판단할 수 있다

6.1.3 안전한 호출 연산자 :  ?.
?. : null 검사와 메소드 호출을 한 번의 연산으로 수행한다
- 호출하려는 값이 null이 아니라면 ?.은 일반 메소드 호출처럼 작동한다
- 호출하려는 값이 null이면 이 호출은 무시되고 null이 결과 값이 된다
- 메소드 호출뿐 아니라 프로퍼티를 읽거나 쓸 때도 안전한 호출을 사용할 수 있다
- 여러 안전한 호출 연산자를 연쇄해 사용할 수 있다

6.1.4 엘비스 연산자 : ?:
- null 대신 사용할 디폴트 값을 지정할 때 편리하게 사용할 수 있는 연산자를 제공
- 엘비스 연산자 = 널 복합 연산자
```
    fun foo(s: String?) {
        val t: String = s ?: ""
    }
```
- 이항 연산자로 좌항을 계산할 값이 널이 검사한다. 좌항 값이 널이 아니면 좌항 값을 결과로 하고, 좌항 값이 널이면 우항 값을 결과로 한다
- 엘비스 연산자를 객체가 널인 경우 널을 반환하는 안전한 호출 연산자와 함께 사용해서 객체가 널인 경우에 대비한 값을 지정하는 경우도 많다
- 코틀린에서는 return이나 thorw 등의 연산도 식이기 때문에 엘비스 연산자의 우항에 연산을 넣을 수 있고, 엘비스 연산자를 더욱 편하게 사용할 수 있다
- 이런 패턴은 전제 조건을 검사하는 경우 특히 유용

6.1.5 안전한 캐스트 : as?
- 어떤 값을 지정한 타입으로 캐스트하는데 값을 대상 타입으로 변환할 수 없으면 null을 반환한다
- 이 패턴을 사용하면 파라미터로 받은 값이 원하는 타입인지 쉽게 검사하고 캐스트할 수 있고, 타입이 맞지 않으면 쉽게 false를 반환할 수 있다

6.1.6 널 아님 단언 : !!
- 코틀린에서 널이 될 수 있는 타입의 값을 다룰 때 사용할 수 있는 도구 중에서 가장 단순하면서 무딘 도구이다
- 느낌표를 이중으로 사용하면 어떤 값이든 널이 될 수 없는 타입으로 강제로 바꿀 수 있다
- 근본적으로 !!는 컴파일러에게 "나는 이 값이 null이 아님을 잘 알고 있다. 내가 잘못 생각했다면 예외가 발생해도 감수하겠다"라고 말하는 것이다
- 어떤 값이 널이었는지 확실히 하기 위해 여러 !! 단언문을 한 줄에 함께 쓰는 일은 지양해야 한다

6.1.7 let 함수
- 널이 될 수 있는 식을 더 쉽게 다룰 수 있다
- let 함수를 안전한 호출 연산자와 함께 사용하면 원하는 식을 평가해서 결과가 널인지 검사한 다음에 그 결과를 변수에 넣는 작업을 간단한 식을 사용해 한꺼번에 처리할 수 있다
- 가장 흔한 용례는 널이 될 수 있는 값을 널 아닌 값만 이자로 받는 함수에 넘기는 경우
```
fun sendEmailTo(email: String) {

}

val email: String? = null
//sendEmailTo(email)
if (email != null) sendEmailTo(email)
```

- let 함수를 통해 인자를 전달할 수도 있다
- 자신의 수신 객체를 인자로 전달받은 람다에게 넘긴다. 널이 될 수 있는 값에 대해 안전한 호출 구문을 사용해 let을 호출하되 널이 될 수 없는 타입을 인자로 받는 람다를 let게 전달한다

- 아주 긴 식 이 있고 그 값이 널이 아닐 때 수행해야 하는 로직이 있을 때 let을 쓰면 훨씬 더 편하다

6.1.8 나중에 초기화할 프로퍼티
- 코틀린에서는 일반적으로 생성자에서 모든 프로퍼티를 초기화해야 한다
- 이를 해결하기 위해 프로퍼티를 나중에 초기화 할 수 있다
- lateInit 변경자를 붙이면 프로퍼티를 나중에 초기화할 수 있다

- 나중에 초기화하는 프로퍼티는 항상 var이여야 한다
- val 프로퍼티는 final 필드로 컴파일되며, 생성자 안에서 반드시 초기화 해야 한다

6.1.9 널이 될 수 있는 타입 확장
- 널이 될 수 있는 타입에 대한 확장 함수를 정의하면 null 값을 다루는 강력한 도구로 활용할 수 있다
- 어떤 메소드를 호출하기 전에 수신 객체 역할을 하는 변수가 널이 될 수 없다고 보장하는 대신, 직접 변수에 대해 메소드를 호출해도 확장 함수인 메소드가 알아서 널을 처리해준다
- 안전한 호출 없이도 널이 될 수 있는 수신 객체 타입에 선언된 확장 함수를 호출 가능하다

input.isnullOrBlank()
널이 될 수 있는 타입의 값.널이 될 수 있는 타입의 확장 함수
--> 안전한 호출을 사용하지 않는다

6.1.10 타입 파라미터의 널 가능성
- 코틀린에서는 함수나 클래스의 모든 타입 파라미터는 기본적으로 널이 될 수 있다
- 널이 될 수 있는 타입을 포함하는 어떤 타입이라도 타입 파라미터를 대신할 수 있다
- 타입 파라미터 T를 클래스나 함수 안에서 타입 이름으로 사용하면 이름 끝에 물음표가 없더라고 T가 널이 될 수 있는 타입이다

6.1.11 널 가능성과 자바

*/


fun main() {
    // 널이 될 수 있는 타입을 인자로 가진 함수
    fun strLenSafe(s: String?) {}

    // if 검사를 통해 null 값 다루기
    fun strLenSafe2(s: String): Int = if (s != null) s.length else 0

    val s: String? = null

    // 같은 코드
    s?.toUpperCase()
    if (s != null) s.toUpperCase()

    // 안전한 호출 연산자 ?.
    fun printAllCaps(s: String?) {
        val allCaps: String? = s?.toUpperCase()
        println(allCaps)
    }

    printAllCaps("abc")
    printAllCaps(null)

    // 널이 될 수 있는 프로퍼티를 다루기 위해 안전한 호출 사용
    class Employee(val name: String, val manager: Employee?)

    fun managerName(employee: Employee): String? = employee.manager?.name

    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))

    // 안전한 호출 연쇄시키기
    //

    // 엘비스 연산자
    fun foo(s: String?) {
        val t: String = s ?: ""
    }

    // 객체가 널이 경우에 대비한 값을 지정한 경우
    fun strLenSafe3(s: String?): Int = s?.length ?: 0
    println(strLenSafe3("abc"))
    println(strLenSafe3(null))

    // 안전한 캐스트
    class Person(val firstName: String, val lastName: String) {
        override fun equals(other: Any?): Boolean {
            val otherPerson = other as? Person ?: return false
            return otherPerson.firstName == firstName && otherPerson.lastName == lastName
        }

        override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
    }

    val p1 = Person("Dmitry", "Jemerov")
    val p2 = Person("Dmitry", "Jemerov")
    println(p1 == p2)
    println(p1.equals(42))

    // 널 아님 단언
    fun ignoreNulls(s: String?) {
        val sNotNull: String = s!!
        println(sNotNull.length)
    }

    //ignoreNulls(null)

    // let 함수
    fun sendEmailTo(email: String) {
        println("Sending email to $email")
    }

    val email: String? = null
    //sendEmailTo(email)
    if (email != null) sendEmailTo(email)

    // let을 안전하게 호출하면 수신 객체가 널이 아닌 경우 람다를 실행
    email?.let { email ->
        sendEmailTo(email)
    }

    email?.let {
        sendEmailTo(it)
    }

    // let을 사용해 null 이 아닌 인자로 함수 호출하기
    var emails: String? = "yole@example.com"
    emails?.let { sendEmailTo(it) }
    emails = null
    emails?.let { sendEmailTo(it) }

    /*val person: Person = getTheBestPersonInTheWorld()
    if (person != null) sendEmailTo(person.email)

    getTheBestPersonInTheWorld()?.let { sendEmailTo(it.email) }*/

    // 나중에 초기화할 프로퍼티
    class MyService{
        fun performAction():String = "foo"
    }

    class MyTest{
        private lateinit var myService: MyService

        fun setUp(){
            myService = MyService()
        }

        fun testAction(){
            //
        }
    }

    // 널이 될 수 있는 타입 확장
    fun verifyUserInput(input : String?){
        if(input.isNullOrBlank()){
            println("Please fill in the required fields")
        }
    }

    verifyUserInput(" ")
    verifyUserInput(null)

    // 타입 파라미터의 널 가능성
    fun <T> printHashCode(t:T){
        println(t?.hashCode())
    }

    printHashCode(null)
}