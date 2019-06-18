package com.example.kotlinstudyproject.chapter4

import android.app.Person

/*

object 키워드 : 클래스 선언과 인스턴스 생성

다양한 상황에서 사용하지만 모든 경우 클래스를 정의하면서 동시에 인스턴스(객체)를 생성한다는 공통점이 있다

- 객체 선언은 싱글턴을 정의하는 방법 중 하나다
- 동반 객체는 인스턴스 메소드는 아니지만 어떤 클래스와 관련 있는 메소드와 팩토리 메소드를 담을 때 쓰인다.
  동반 객체 메소드에 접근할 때는 동반 객체가 포함된 클래스의 이름을 사용할 수 있다
- 객체 식은 자바의 무명 내부 클래스 대신 쓰인다


1 객체 선언 : 싱글턴 쉽게 만들기

인스턴스가 하나만 필요한 클래스가 유용한 경우가 많다
코틀린은 객체 선언 기능을 통해 싱글턴을 언어에서 기본 지원한다
객체 선언은 클래스 선언과 그 클래스에 속한 단일 인스턴스의 선언을 합친 선언이다

클래스와 마찬가지로 객체 선언 안에도 프로퍼티, 메소드, 초기화 블록이 들어갈 수 있다
하지만 생성자는 객체 선언에 쓸 수 없다
일반 클래스 인스턴스와 달리 싱글턴 객체 선언문이 있는 위치에서 생성자 호출 없이 즉시 만들어진다

*/

object PayRoll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) {

        }
    }
}


/*

2 동반 객체 : 팩토리 메소드와 정적 멤버가 들어갈 장소

코틀린 클래스 안에는 정적인 멤버가 없다
코틀린 언어는 자바 static 키워드를 지원하지 않는다
대신 패키지 수준의 최상위 함수와 객체 선언을 활용
대부분의 경우 최상위 함수를 활용하는 편을 더 권장한다

companion이라는 표시를 붙이면 그 클래스의 동반 객체로 만들 수 있다
동반 객체의 프로퍼티나 메소드에 접근하려면 그 동반 객체가 정의된 클래스 이름을 사용
동반 객체의 멤버를 사용하는 구문은 자바의 정적 메소드 호출이나 정적 필드 사용 구문과 같아진다

*/

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }

    fun bar2() {

    }
}

class User7 {
    val nickname: String

    constructor(email: String) {
        nickname = email.substringBefore('@')
    }
}

class User8 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = User8(email.substringBefore('@'))
    }
}
/*
3 동반 객체를 일반 객체처럼 사용

동반 객체는 클래스 안에 정의된 일반 객체이다
따라서 동반 객체에 이름을 붙이거나 , 동반 객체가 인터페이스를 상속하거나, 동반 객체 안에 확장 함수와 프로퍼티를 정의할 수 있다

- 동반 객체에서 인터페이스 구현
- 동반 객체 확장

4 객체 식 : 무명 내부 클래스를 다른 방식으로 작성

무명 객체를 정의할 때도 object 키워드를 사용한다
무명 객체는 자바의 무명 내부 클래스를 대신한다


*/

class Person(val name: String) {
    companion object Loader {
        //fun fromJSON(jsonText : String) : Person = ...
    }
}


/*  객체 식
fun countClicks(window : Window){
    var clickCount = 0

    window.addMouseListener(object : MouseAdapter(){
        override fun mouseClicked(e: MouseEvent){
            clickCount++
        }
    })
}
*/


fun main() {
    // PayRoll.allEmployees.add(Person(...))
    // PayRoll.calculateSalary()

    A.bar()

    val a = A()
    a.bar2()

    val subscribingUser = User8.newSubscribingUser("bob@gmail.com")
    println(subscribingUser.nickname)


}
