package com.example.kotlinstudyproject.Chapter5

import java.lang.StringBuilder

/*
5.5 수신 객체 지정 람다 : with 와 apply
수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메소드를 호출할 수 있게 하는 것
그럼 람다를 수신 객체 지정 람다(lambda with receiver)라고 부른다

5.5.1 with 함수
- 어떤 객체의 이름을 반복하지 않고 그 객체에 대해 다양한 연산을 수행
- with가 반환하는 값은 람다 코드를 실행한 결과며, 그 결과는 람다 식의 본문에 있는 마지막 식의 값이다

5.5.2 apply 함수
- 항상 자신에게 전달된 객체를 반환한다

*/

fun main() {
    // with 함수
    fun alpabetWith(): String {
        val stringBuilder = StringBuilder()
        return with(stringBuilder) {
            for (letter in 'A'..'Z') {
                this.append(letter)
            }
            append("\nNow I konw the aplabet!")
            this.toString()
        }
    }

    fun alpabetApply(): String = StringBuilder().apply {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I konw the aplabet!")
    }.toString()

}