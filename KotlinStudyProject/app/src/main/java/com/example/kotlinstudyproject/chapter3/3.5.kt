package com.example.kotlinstudyproject.chapter3

/*

문자열과 정규식 다루기

코틀린 문자열은 자바 문자열과 같다

1 문자열 나누기
코틀린에서는 toRegex 확장함수를 사용해 문자열을 정규식으로 변환할 수 있다.
split 확장 함수를 오버로딩한 버전 중에는 구분 문자열을 하나 이상 인자로 받는 함수가 있다

2 정규식과 3중 따옴표로 묶은 문자열
- String을 확장한 함수를 사용하고
- 정규식 사용

3 여러 줄 3중 따옴표 문자열

줄 바꿈이 들어있는 프로그램 텍스트를 쉽게 문자열로 만들 수 있다


*/

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir : $directory, name : $fileName, ext: $extension")
}

fun main() {
    println("12.345-6.A".split(".", "-"))

    parsePath("/Users/yole/kotlin-book/chapter.adoc")

    val kotlinLogo =
        """|  //
            .| //
            .|/ \"""

    println(kotlinLogo.trimMargin("."))


}