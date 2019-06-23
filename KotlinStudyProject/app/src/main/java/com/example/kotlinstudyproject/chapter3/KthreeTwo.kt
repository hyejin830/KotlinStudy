package com.example.kotlinstudyproject.chapter3

fun main() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, ";", "(", ")"))

    /* 첫번째 문제: 함수 호출 부분의 가독성
        - 코틀린으로 작성한 함수를 호출할 때는 함수에 전달하는 인자 중 일부 또는 전부의 이름을 명시할 수 있다
        - 호출 시 인자 중 어느 하나라도 이름을 명시하고 나면 혼동을 막기 위해 그 뒤에 오는 모든 인자는 이름을 꼭 명시 */
    println(joinToString(list, separator = "", prefix = "", postfix = "."))

    // 두번재 : 디폴트 파라미터 값
    println(joinToString(list))

    /* 함수를 선언할 때와 같은 순서로 인자를 지정해야 한다.
       이름 붙인 인자를 사용하는 경우에는 인자 목록의 중간에 있는 인자를 생략하고, 지정하고 싶은 인자를 이름을 붙여서 순서와 관계 없이 지정할 수 있다 */
    println(joinToString(list, postfix = "]", prefix = "["))

    // 자바에서 코틀린 함수를 자주 호출해야 한다면 자바 쪽에서 좀 더 편하게 코틀린함수를 호출할 수 있다 : @JvmOverloads 애노테이션을 함수에 추가
    performOperation()
    reportOperationCount()
}

// 최상위 프로퍼티
var opCount = 0


fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}

// 접근은 getter, setter
var UNIX_LINE_SEPARATOR_1 = "\n"

// 상수 필드
const val UNIX_LINE_SEPARATOR = "\n"

/*
* 이 함수는 제네릭하다 : 이 함수는 `어떤 타입의 값`을 원소로 하는 컬렉션이든 처리할 수 있다
* */


@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ",", // 디폴트 값
    prefix: String = "", // 디폴트 값
    postfix: String = "" // 디폴트 값

): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)

    return result.toString()
}


