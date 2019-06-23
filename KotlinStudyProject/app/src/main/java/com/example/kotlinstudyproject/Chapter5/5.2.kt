package com.example.kotlinstudyproject.Chapter5

/*
5.2 컬렉션 함수형 API

함수형 프로그래밍 스타일을 사용하면 컬렉션을 다룰 때 편리하다
컬렉션을 다루는 코틀린 표준 라이브러리를 살펴본다

5.2.1 필수적인 함수 : filter 와 map
- filter 함수 : 컬렉션을 이터레이션하면서 주어진 람다에 각 원소를 넘겨서 람다가 true를 반환하는 원소만 모은다
결과는 입력 컬렉션의 원소 중에서 주어진 술어를 만족하는 원소로만 이뤄진 새로운 컬렉션이다

- map 함수 : 원소를 변환하려면 map 함수를 사용 , 주어진 람다를 컬렉션의 각 원소에 적용한 결과를 모아서 새 컬렉션으로 만든다

키와 값을 처리하는 함수가 따로 존재한다
filterKeys와 mapKeys는 키를 걸러내거나 변환하고
filterValues와 mapValues는 값을 걸러 내거나 변환한다

5.2.2 all, any, count, find : 컬렉션에 술어 적용
모든 원소가 어떤 조건을 만족하는지 판단하는 연산

- all 함수 : 모든 원소가 술어를 만족하는지
- any 함수 : 술어를 만족하는 원소가 하나라도 있는지
- count 함수 : 조건을 만족하는 원소의 개수를 반환
- find 함수 : 조건을 만족하는 첫 번째 원소를 반환

5.2.3 groupBy : 리스트를 여러 그룹으로 이뤄진 맵으로 변경
컬렉션의 모든 원소를 어떤 특성에 따라 여러 그룹으로 나누고 싶을 때 이용

5.2.4 flapMap과 flatten : 중첩된 컬렉션 안의 원소 처리
- flatMap 함수 : 인자로 주어진 람다를 컬렉션의 모든 객체에 적용하고(매핑하고) 람다를 적용한 결과 얻어지는 여러 리스트를 한 리스트로 한데 모은다(펼치기)

*/

data class Person(val name: String, val age: Int)


fun main() {
    // filter 함수
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })

    // map 함수
    println(list.map { it * it })

    println(people.map { it.name })
    people.map(Person::name)

    // 연쇄 호출
    people.filter { it.age > 30 }.map(Person::name)

    // 최대값 계산 코드
    val maxAge = people.maxBy(Person::age)!!.age
    people.filter { it.age == maxAge }

    // 맵의 키와 값을 처리하는 함수
    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.toUpperCase() })

    // ---------------------------------------------------------------

    val canBeInClub27 = { p: Person -> p.age <= 27 }

    // all 함수
    println(people.all(canBeInClub27))
    // any 함수
    println(people.any(canBeInClub27))

    // !all을 수행한 결과와 그 조건의 부정에 대해 any를 수행한 결과는 같다
    println(!list.all { it == 3 })
    println(list.any { it != 3 })

    // count
    println(people.count(canBeInClub27))

    // find 함수
    println(people.find(canBeInClub27))

    // ---------------------------------------------------------------

    // groupBy 함수
    val peoples = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    println(peoples.groupBy { it.age })

    val lists = listOf("a", "ab", "b")
    println(lists.groupBy(String::first))

    // ---------------------------------------------------------------

    // flatMap 함수
    class Book(val title: String, val authors: List<String>)

    val books = listOf(
        Book("Thursday Nest", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )
    println(books.flatMap { it.authors }.toSet())

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

}