package com.example.kotlinstudyproject.chapter3

import android.view.View
import android.widget.Button

/* 확장 함수 (extension function)
 - 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스의 밖에 선언된 함수 */

fun String.lastChar(): Char = this.get(this.length - 1)
fun Int.lastChar(): Int = this.minus(3)

/* 추가하려는 함 수 이름 앞에 그 함수가 확장할 클래스의 이름을 덧붙이기만 하면 된다
   - 클래스의 이름 : 수신 객체 타입 (receiver type)
   - 확장 함수가 호출되는 대상이 되는 값(객체)를 수신 객체 (receiver object)
   수신 객체의 this 는 생략 가능 */

fun main() {
    println("hyejin".lastChar())
    // 수신 객체 : hyejin , 수신 객체 타입 : String
    println(3.lastChar())

    // 확장 함수로 유틸리티 함수 정의
    val list = listOf("1,2,3")
    println(list.joinToString2(separator = ";", prefix = "(", postfix = ")"))
    println(list.joinToString2())

    // join 함수
    println(listOf("one", "two", "eight").join(""))

    /* 확장 함수는 오버라이드 할 수 없다
     멤버 함수를 오버라이드 할 경우에는 오버라이드 한 함수가 호출되지만
     확장 함수인 경우 같은 확장함수를 기반 클래스와 하위 클래스에 대해 정의했어도
     호출할 때는 수신 객체로 지정한 변수의 정적 타입에 의해 어떤 확장 함수가 호출될지 결정되지
     그 변수에 저장된 객체의 동적인 타입에 의해 확장 함수가 결정 되지 않는다 */
    fun View.showOff() = println("I'm a view!")

    fun Button.showOff() = println("I'm a Button!")

    /* 5 확장 프로퍼티
         기존 클래스 객체에 대한 프로퍼티 형식의 구문으로 사용할 수 있는 API를 추가할 수 있다 */
    println("hyejin".lastChar())
    val sb = StringBuilder("Hyejin?")
    sb.lastChar = '!'
    println(sb)

}

// 변경 가능한 확장 프로퍼티 선언하기
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }


fun <T> Collection<T>.joinToString2(
    separator: String = ",", // 디폴트 값
    prefix: String = "", // 디폴트 값
    postfix: String = "" // 디폴트 값

): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)

    return result.toString()
}

// 문자열 컬렉션에 대해서만 호출할 수 있는 join 함수
fun Collection<String>.join(
    separator: String = ",", // 디폴트 값
    prefix: String = "", // 디폴트 값
    postfix: String = "" // 디폴트 값
) = joinToString2(separator, prefix, postfix)
