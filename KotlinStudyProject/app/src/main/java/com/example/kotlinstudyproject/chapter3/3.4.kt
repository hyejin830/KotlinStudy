package com.example.kotlinstudyproject.chapter3

/* 3.4 컬렉션 처리 : 가변 길이 인자, 중위 함수 호출, 라이브러리 지원

- vararg 키워드를 사용하면 호출 시 인자 개수가 달라질 수 있는 함수를 정의할 수 있다
- 중위 함수 호출 구문을 사용하면 인자가 하나뿐인 메소드를 간편하게 호출할 수 있다
- 구조 분해 선언을 사용하면 복합적인 값을 분해해서 여러 번 수에 나눠 담을 수 있다
* */

/*
  2 가변 인자 함수 : 인자의 개수가 달라질 수 있는 함수 정의
  - 자바는 타입 뒤에 .. 를 붙이는 대신, 코틀린에서는 파라미터 앞에 vararg 변경자를 붙인다
  - 배열인 경우 : 코틀린에서는 배열을 명시적으로 풀어서 배열의 각 원소가 인자로 전달되게 한다 --> 스프레드 연산자
  - 실제로 전달하려는 배열 앞에 *를 붙이기만 하면 된다

  3 값의 쌍 다루기 : 중위 호출과 구조 분해 선언
    - 인자가 하나뿐인 일반 메소드나 인자가 하나뿐인 확장 함수에 중위 호출을 사용할 수 있다
    - infix 변경자를 함수 선언 앞에 추가
 */

fun test(vararg value: Int){

}

infix fun Any.to(other: Any) = Pair(this, other)

fun main() {
    // 구조 분해 선언
    val (number, name) = 1 to true

    //test(1,2,3)

    val intArray = IntArray(3)
    intArray.set(1,1)
    test(*intArray)
}

