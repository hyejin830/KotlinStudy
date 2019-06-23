package com.example.kotlinstudyproject.Chapter5

/*
5.3 지연 계산(lazy) 컬렉션 연산
컬렉션 함수는 결과 컬렉션을 즉시 생성한다
컬렉션 함수를 연쇄하면 매 단계마다 계산 중간 결과를 새로운 컬렉션에 임시로 담는다는 말이다
시퀀스를 사용하면 중간 임시 컬렉션을 사용하지 않고도 컬렉션 연산을 연쇄할 수 있다

5.3.1 시퀀스 연산 실행 : 증간 연산과 최종 연산
- 시퀀스에 대한 연산은 중간 연산과 최종 연산으로 나뉜다
- 중간 연산은 다른 시퀀스를 반환한다
- 최종 연산은 결과를 반환한다
- 결과는 최초 컬렉션에 대해 변환을 적용한 시퀀스로부터 일련의 계산을 수행해 얻을 수 있는 컬렉션이나 원소,숫자 또는 객체이다
- 중간 연산은 항상 지연 계산된다

- 직접 연산과 시퀀스의 경우의 차이
직접 연산(즉시 계산) : map 함수를 각 원소에 대해 먼저 수행해서 새 시퀀스를 얻고, 그 시퀀스에 다시 filter함수를 수행
시퀀스(지연 계산) : 모든 연산은 각 원소에 대해 순차적으로 적용. 즉, 첫 번째 원소가 처리되고, 다시 두 번째 원소가 처리, 이런 처리가 모든 원소에 대해 적용

5.3.2 시퀀스 만들기
- 시퀀스를 만드는 다른 방법은 generateSequence 함수를 사용

*/

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    people.map(Person::name).filter { it.startsWith("A") }

    people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("A") }
        .toList()

    // 최종 연산이 없는 예제
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }

    // 최종 연산을 호출하면 연기됐던 모든 계산이 수행
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
        .toList()

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo10 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo10.sum())

}