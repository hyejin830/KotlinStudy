package com.example.kotlinstudyproject.chapter6

/*

6.3 컬렉션과 배열
코틀린 컬렉션이 자바 라이브러리를 바탕으로 만들어졌고 확장 함수를 통해 기능을 추가한다는 사실을 배웠다
코틀린의 컬렉션 지원과 자바와 코틀린 컬렉션 간의 관계에 대해 이야기할 내용이 남아있다

6.3.1 널 가능성과 컬렉션
- 타입 인자 안에서 ?가 하는 일을 이해하기 위해 파일의 각 줄을 읽어서 숫자로 변환하기 위해 파싱하는 다음 예제
- List<Int?>는 Int? 타입의 값을 저장할 수 있다. 다른 말로 하면 리스트에는 Int나 Null을 저장할 수 있다

- 리스트 안의 각 값이 널이 될 수 있다 : List<Int?>
- 전체 리스트가 널이 될 수 있다 : List<Int>?

- 널 값과 아닌 값을 분류할 수 있다

6.3.2 읽기 전용과 변경 가능한 컬렉션
- 코틀린에서는 컬렉션 안의 데이터에 접근하는 인터페이스와 컬렉션 안의 데이터를 변경하는 인터페이스를 분리했다는 점
- kotlin.collections.Collection
- Collection 인터페이스 : 컬렉션 안의 원소에 대해 이터레이션, 컬렉션의 크기를 얻고, 어떤 값이 컬렉션 안에 들었는지 검사하고, 컬렉션에서 데이터를 읽는 다른 연산을 수행
  그러나 원소를 추가하거나 제거하는 메소드는 없다
- kotlin.collections.MutableCollection
- MutableCollection : 일반 인터페이스인 kotlin.collections.Collection 확장하면서 원소를 추가, 삭제, 컬렉션 안의 원소를 모두 지우는 등의 메소드를 더 제공

- 일반적인 규칙으로는 항상 읽기 전용 인터페이스를 사용하는 것
- 어쩌면 원본의 변경을 막기 위해 컬렉션을 복사해야 할 수 도 있다 : 방어적 복사(defensive copy)

- 컬렉션 인터페이스를 사용할 때 항상 염두에 둬야 할 핵심은 읽기 전용 컬렉션이라고 해서 꼭 변경 불가능한 컬렉션일 필요는 없다는 것
- 읽기 전용 컬렉션이 항상 스레드 안전하지는 않다는 것

6.3.3 코틀린 컬렉션과 자바
- 컬렉션의 변경 가능성과 관련해 중요한 문제가 생긴다
- 컬렉션을 자바로 넘기는 코틀린 프로그램을 작성한다면 호출하려는 자바 코드가 컬렉션을 변경할지 여부에 따라 올바른 파라미터 타입을 사용할 책임은 여러분에게 있다

6.3.4 컬렉션을 플랫폼 타입으로 다루기
- 자바 코드에서 정의한 타입을 코틀린에서는 플랫폼 타입으로 본다
플랫폼 타입의 경우 코틀린 쪽에는 널 관련 정보가 없다
따라서 컴파일러는 코틀린 코드가 그 타입을 널이 될 수 있는 타입이나 널이 될 수 없는 타입 어느 쪽으로든 사용할 수 있게 허용

- 자바쪽에서 선언한 컬렉션 타입의 변수를 코틀린에서는 플랫폼타입으로 본다
플랫폼 타입인 컬렉션은 기본적으로 변경 가능성에 대해 알 수 없다
따라서 코틀린 코드는 그 타입을 읽기 전용 컬렉션이나 변경 가능한 컬렉션 어느쪽으로든 다를 수 있다

6.3.5 객체의 배열과 원시 타입의 배열
- 코틀린 배열은 타입 파라미터를 받는 클래스
- 배열의 원소 타입은 바로 그 타입 파라미터에 의해 정해진다

- 코틀린에서 배열을 만드는 방법
 - arrayOf함수에 원소를 넘기면 배열을 만들 수 있다
 - arrayOfNulls 함수에 정수 값을 인자로 넘기면 모든 원소가 null이고 인자로 넘긴 값과 크기가 같은 배열을 만들 수 있다
 - Array 생성자는 배열 크기와 람다를 인자로 받아서 람다를 호출해서 각 배열 원소를 초기화해준다

- 코틀린에서는 배열을 인자로 받는 자바 함수를 호출하거나 vararg 파라미터를 받는 코틀린 함수를 호출
- 데이터가 이미 컬렉션에 들어 있다면 컬렉션을 배열로 변환해야 한다 : toTypedArray메소드

- 코틀린은 원시 타입의 배열을 표현하는 별도 클래스를 각 원시 타입마다 하나씩 제공한다
 - Int 타입의 배열은 IntArray ,ByteArray, CharArray, BooleanArray
- 원시 타입의 배열을 만드는 방법
 - 각 배열 타입의 생성자는 size 인자를 받아서 해당 원시 타입의 디폴트 값으로 초기화된 size 크기의 배열을 반환
 - 팩토리 함수는 여러 값을 가변 인자로 받아서 그런 값이 들어간 배열을 반환
 - 크기와 람다를 인자로 받는 생성자를 사용

- 코틀린 표준 라이브러리는 배열 기본 연산(배열 길이 구하기, 원소 설정하기, 원소 읽기) 에 대해 컬렉션에 사용할 수 있는 모든 확장 함수를 배열에도 제공

*/

fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is : ${args[i]}")
    }

    // toTypedArrary 메소드
    val strings = listOf("a", "b", "c")
    println("%s%s%s".format(*strings.toTypedArray()))

    // 각 배열 타입의 생성자는 size 인자를 받아서 해당 원시 타입의 디폴트 값으로 초기화된 size 크기의 배열을 반환
    val fiveZeros = IntArray(5)

    // 팩토리 함수는 여러 값을 가변 인자로 받아서 그런 값이 들어간 배열을 반환
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)

    // 크기와 람다를 인자로 받는 생성자를 사용
    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString())
}