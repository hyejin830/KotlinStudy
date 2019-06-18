package com.example.kotlinstudyproject.chapter4

/*

컴파일러가 생성한 메소드 : 데이터 클래스와 클래스 위임


 1 모든 클래스가 정의해야 하는 메소드
  코틀린은 이러한 메소드 구현을 자동으로 생성해줄 수 있다


*/

class Client(val name: String, val postalCode: Int) {
    override fun toString(): String = "Client(name=$name, postalCode = $postalCode)"

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) return false
        return name == other.name && postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}

/*

문자열 표현 : toString()

자바처럼 코틀린의 모든 클래스도 인스턴스의 문자열 표현을 얻을 방법을 제공한다
주로 디버깅과 로깅 시 이 메소드를 사용
기본 구현을 바꾸려면 toString 메소드를 오버라이드 해야한다

*/

/*

객체의 동등성 : equals()

Client 클래스를 사용하는 모든 계산은 클래스 밖에서 이뤄진다

*/

/*

해시 컨테이너 : hashCode()

JVM언어에서는 hashCode가 지켜야 하는 "equals()가 true를 반환하는 두 객체는 반드시 같은 hashCode()를 반환해야 한다"라는 제약이 있다
HashSet은 원소를 비교할 때 비용을 줄이기 위해 먼저 객체의 해시 코드를 비교하고 해시 코드가 같은 경우에만 실제 값을 비교한다
원소 객체들이 해시 코드에 대한 규칙을 지키지 않는 경우 HashSet은 제대로 작동할 수 없다
*/

fun main2() {

    val client = Client("Hyejin", 123)
    println(client)

    // 두 객체는 동일하지 않다
    val client1 = Client("정혜진", 123)
    val client2 = Client("정혜진", 123)
    println(client1 == client2)

}

/*
 2 데이터 클래스 : 모든 클래스가 정의해야 하는 메소드 자동 생성

 data 라는 변경자를 클래스 앞에 붙이면 필요한 메소드를 컴파일러가 자동으로 만들어준다
 data 변경자가 붙은 클래스를 데이터 클래스라고 한다

 - 인스턴스 간 비교를 위한 equals
 - HashMap과 같은 해시 기반 컨테이너에서 키로 사용할 수 있는 HashCode
 - 클래스의 각 필드를 선언 순서대로 문자열 표현을 만들어주는 toString

 데이터 클래스는 몇 가지 유용한 메소드를 더 생성해준다

 데이터 클래스와 불변성 : copy() 메소드

  데이터 클래스의 모든 프로퍼티를 읽기 전용으로 만들어서 데이터 클래스를 불변클래스로 만들라고 권장한다
  데이터 클래스 인스턴스를 불변 객체로 더 쉽게 활용할 수 있게 코틀린 컴파일러는 한 가지 편의 메소드를 제공한다
  객체를 복사하면서 일부 프로퍼티를 바꿀 수 있게 해주는 copy 메소드다
  복사를 하면서 일부 프로퍼티 값을 바꾸거나 복사본을 제거해도 프로그램에서 원본을 참조하는 다른 부분에 전혀 영향을 끼치지 않는다

*/

data class Client2(val name: String, val postalCode: Int)

fun main3() {
    val lee = Client2("이계영", 222)
    println(lee.copy(postalCode = 4000))

}

/*
 3 클래스 위임 : by 키워드 사용

 상속을 허용하지 않는 클래스에 새로운 동작을 추가해야 할 때가 있다
 이럴 때 사용하는 일반적인 방법이 데코레이터 패턴이다
 이 패턴의 핵심은 상속을 허용하지 않는 클래스 대신 사용할 수 있는 새로운 클래스를 만들되
 기존 클래스와 같은 인터페이스를 데코레이터가 제공하게 만들고,
 기존 클래스를 떼코레이터 내부에 필드로 유지하는 것

 이 때 새로 정의해야 하는 기능은 데코레이터의 메소드에 새로 정의하고 기존 기능이 그대로 필요한 부분은 데코레이터의 메소드가
 기존 클래스의 메소드에게 요청을 전달한다



*/

class DelegatingCollection<T>(
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var obejctsAdded = 0

    override fun add(element: T): Boolean {
        obejctsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        obejctsAdded += c.size
        return innerSet.addAll(c)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 2, 3))
    println("${cset.obejctsAdded} objects were added, ${cset.size} remain")

}