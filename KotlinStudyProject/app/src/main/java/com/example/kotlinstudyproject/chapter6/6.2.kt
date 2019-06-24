package com.example.kotlinstudyproject.chapter6

import java.lang.IllegalArgumentException

/*
6.2 코틀린의 원시 타입

Int, Boolean, Any 등의 원시 타입

- 코틀린은 원시 타입과 래퍼 타입을 구분하지 않는다
- Object, Void 등의 자바 타입과 코틀린 타입 간의 대응 관계

6.2.1 원시 타입 : Int, Boolean 등
- 자바는 원시 타입과 참조 타입을 구분한다
 - 원수 타입의 변수에는 그 값이 직접 들어가지만, 참조 타입의 변수에는 메모리 상의 객체 위치가 들어간다
- 코틀린은 원시 타입과 래퍼 타입을 구분하지 않으므로 항상 같은 타입을 사용한다
- 더 나아가 코틀린에서는 숫자 타입 등 원시 타입의 값에 대해 메소드를 호출할 수 있다
- 실행 시점에 숫자 타입은 가능한 한 가장 효율적인 방식으로 표현된다
  대부분의 경우 코틀린의 Int 타입은 자바 int 타입으로 컴파일 된다
  이런 컴파일이 불가능한 경우는 컬렉션과 같은 제네릭 클래스를 사용하는 경우뿐
  예) Int 타입을 컬렉션의 타입 파라미터로 넘기면 그 컬렉션에는 Int의 래퍼 타입에 해당하는 java.lang.Integer 객체에 들어간다

6.2.2 널이 될 수 있는 원시 타입 : Int?, Boolean? 등
- null 참조를 자바의 참조 타입의 변수에만 대입할 수 있기 때문에 널이 될 수 있는 코틀린 타입은 자바 원시 타입으로 표현할 수 없다
- 코틀린에서는 널이 될 수 있는 원시 타입을 사용하면 그 타입은 자바의 래퍼타입으로 컴파일 된다

6.2.3 숫자 변환
- 코틀린과 자바의 가장 큰 차이점 중 하나는 숫자를 변환하는 방식이다
- 코틀린은 한 타입의 숫자를 다른 타입의 숫자로 자동 변환하지 않는다

- 코틀린은 모든 원시 타입에 대한 변환 함수를 제공한다
  변환 함수의 이름은 toByte(), toShort(), toChar() 등과 같다
- 양방향 변환 함수가 모두 제공
  어떤 타입을 더 표현 범위간 넓은 타입으로 변환하는 함수 : Int.toLong()
  타입을 범위가 더 표현 범위가 좁은 타입으로 변환하면서 값을 벗어나는 경우에는 일부를 잘라내는 함수 : Long.toInt()

- 숫자 리터널을 사용할 때는 보통 변환 함수를 호출할 피룡가 없다.
- 직접 변환하지 않아도 숫자 리터럴을 타입이 알려진 변수에 대입하거나 함수에게 인자로 넘기면 컴파일러가 필요한 변환을 자동으로 넣어준다

6.2.4 Any, Any? : 최상위 타입
- 자바에서 Object가 클래스 계층의 최상위 타입이듯 코틀린에서는 Any 타입이 모든 널이 될 수 없는 타입의 조상 타입이다
- 코틀린에서는 Any가 Int 등의 원시 타입을 포함한 모든 타입의 조상 타입
- Any가 널이 될 수 없는 타입임에 유의해야 한다
- 널을 포함하는 모든 값을 대입할 변수를 선언하려면 Any?타입을 사용해야 한다

6.2.5 Unit 타입 : 코틀린의 void
- 자바 void와 같은 기능
- 관심을 가질 만한 내용을 전혀 반환하지 않는 함수의 반환 타입으로 Unit을 쓸 수 있다
- 코틀린 함수의 반환 타입이 Unit이고 그 함수가 제네릭 함수를 오버라이드 하지 않는다면 그 함수는 내부에서 자바 void함수로 컴파일 된다
- 코틀린의 Unit과 자바 void의 차이점
 - 모든 기능을 갖는 일반적인 타입, Unit을 타입 인자로 쓸 수 있다.
 - Unit 타입에 속한 값은 단 하나뿐이며, 그 이름도 Unit이다. Unit 타입의 함수는 Unit 값을 묵시적으로 반환한다

6.2.6 Noting 타입 : 이 함수는 결코 정상적으로 끝나지 않는다
- 결코 성공적으로 값을 돌려주는 일이 없으므로 `반환 값`이라는 개념 자체가 의미 없는 함수가 일부 존재
- Noting 타입은 아무 값도 포함하지 않는다
- 따라서 함수의 반환 타입이나 반환 타입으로 쓰일 타입 파라미터로만 쓸 수 있다
- Nothing을 반환하는 함수를 엘비스 연산자의 우항에 사용해서 전제 조건을 검사할 수 있다
 - 컴파일러는 Nothing 이 반환 타입인 함수가 결코 정상 종료되지 않음을 알고 그 함수를 호출하는 코드를 분석할 때 사용한다
*/

fun main() {
    // 원시 타입
    val i: Int = 1
    val list: List<Int> = listOf(1, 2, 3)

    fun showProgress(progress: Int) {
        val percent = progress.coerceIn(0, 100)
        println("We're ${percent}% done!")
    }

    showProgress(146)

    // 널이 될 수 있는 원시 타입
    data class Person(val name: String, val age: Int? = null) {
        fun isOlderThan(other: Person): Boolean? {
            if (age == null || other.age == null) {
                return null
            }
            return age > other.age
        }
    }

    println(Person("Sam", 35).isOlderThan(Person("Amy", 42)))
    println(Person("Sam", 35).isOlderThan(Person("Jane")))

    // 숫자 변환
    val x = 1
    val list2 = listOf(1L, 2L, 3L)
    println(x.toLong() in list2)

    fun foo(l: Long) = println(l)

    val b: Byte = 1
    val l = b + 1L
    foo(42)

    // Any
    val answer: Any = 42

    // Unit
    fun f(): Unit {

    }

    class NoResultProcessor : Processor<Unit> {
        override fun process() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    // Noting
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    fail("Error occurred")

    //val address = company.address ?: fail("No Address")


}

interface Processor<T> {
    fun process(): T
}