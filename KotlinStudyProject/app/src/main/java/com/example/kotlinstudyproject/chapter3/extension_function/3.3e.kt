package com.example.kotlinstudyproject.extension_function

import com.example.kotlinstudyproject.chapter3.lastChar
import com.example.kotlinstudyproject.chapter3.lastChar as last

// 확장 함수를 사용하기 위해서는 임포트해야 한다
// 같은 이름일때는 as키워드를 사용하여 다른 이름으로 호출할 수 있다

val c = "Hyejin".last()
val d = 5.lastChar()

fun main() {
    println(c)
    println(d)
}