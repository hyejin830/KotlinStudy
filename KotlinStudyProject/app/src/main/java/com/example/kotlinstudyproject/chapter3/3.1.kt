package com.example.kotlinstudyproject

fun main() {
    // 숫자로 이뤄진 집합
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set)
    println(list)
    println(map)

    /*
    - 자바 컬렉션 활용
        - 자바 코드와 상호작용 용이
    */

    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers = setOf(1, 14, 2)
    println(numbers.max())

}
