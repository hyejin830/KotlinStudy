package com.example.kotlinstudyproject.Chapter5

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.support.v4.app.Person
import android.support.v7.app.AppCompatActivity
import android.widget.Button

/*

5장 : 람다로 프로그래밍

# 다루는 내용
- 람다 식과 멤버 참조
- 함수형 스타일로 컬렉션 다루기
- 시퀀스 : 지연 컬렉션 연산
- 자바 함수형 인터페이스를 코틀린에서 사용
- 수신 객체 지정 람다 사용


람다식 또는 람다 : 다른 함수에 넘길 수 있는 작은 코드 조각

- 람다를 사용하면 쉽게 공통 코드 구조를 라이브러리 함수로 뽑아낼 수 있다
- 코틀린 표준 라이브러리는 람다를 아주 많이 사용
- 람다를 자주 사용하는 경우 : 컬렉션 처리
--> 컬렉션을 처리하는 패턴을 표준 라이브러리 함수에 람다를 넘기는 방식으로 대치하는 예제를 다수 살펴봄

5.1 람다식과 멤버 참조

5.1.1 람다 소개 : 코드 블록을 함수 인자로 넘기기

일련의 동작을 변수에 저장하거나 다른 함수에 넘겨야 하는 경우가 자주 있다
자바에서는 무명 내부 클래스를 통해 이런 목적을 달성

함수형 프로그래밍에서는 함수를 값처럼 다루는 접근 방법을 택함으로써 이 문제를 해결
함수를 직접 다른 함수에 전달 할 수 있기 때문에
람다 식을 사용하면 코드가 더욱 간결해진다

람다 식을 사용하면 함수를 선언할 필요가 없고 코드 블록을 직접 함수 인자로 전달할 수 있다

```
button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
```

```
button.setOnClickListener {  }
```

5.1.2 람다와 컬렉션

- 람다를 사용해 컬렉션 검색

```
println(people.maxBy { it.age })
```

- 모든 컬렉션에 대해 maxBy 함수를 호출할 수 있다

- 단지 함수나 프로퍼티를 반환하는 역할을 수행하는 람다는 멤버 참조로 대치할 수 있다
```
people.maxBy(Person::age)
```

5.1.3 람다 식의 문법

- 값처럼 여기저기 전달할 수 있는 동작의 모음
- { x : Int, y : Int -> x + y }
- { 파라미터 -> 본문 }
- 항상 중괄호 사이에 위치
- 화살표(->)가 인자 목록과 람다 본문은 구분

- 정식으로 람다를 작성
```
people.maxBy ({ p : Person -> p.age })
```

개선 적용하기 : 중괄호
- 코틀린에는 함수 호출 시 맨 뒤에 있는 인자가 람다 식이라면 그 람다를 괄호 밖으로 빼낼 수 있다는 문법 관습이 있다
- 람다가 유일한 인자이므로 따라서 괄호 뒤에 람다를 둘 수 있다
people.maxBy() { p : Person -> p.age }

- 람다가 유일한 인자이고 괄후 뒤에 람다를 썻다면 호출 시 빈 괄호를 없애도 된다
people.maxBy { p : Person -> p.age }

개선 적용하기 : 파라미터 타입
- 컴파일러는 람다 파라미터의 타입도 추론할 수 있다
people.maxBy { p -> p.age } // 파라미터 타입을 생략

- 람다의 파라미터 이름을 디폴트 이름인 it으로 바꾸면 람다 식을 더 간단하게 만들 수 있다
- 람다의 파라미터가 하나뿐이고 그 타입을 컴파일러가 추론할 수 있는 경우 it을 바로 쓸 수 있다
people.maxBy { it.age }

- 람다를 변수에 저장할 때는 파라미터 타입을 추론할 문맥이 존재하지 않기 때문에 명시해야 한다

- 본문이 여러 줄로 이뤄진 경우 본문의 맨 마지막에 있는 식이 람다의 결과 값이 된다


5.1.4 현재 영역에 있는 변수에 접근

- 자바 메소드 안에서 무명 내부 클래스를 정의할 때 메소드의 로컬 변수를 무명 내부 클래스에서 사용할 수 있다
- 람다를 함수 안에서 정의하면 함수의 파라미터 뿐만아니라 람다 정의의 앞에 선언된 로컬 변수까지 람다에서 모두 사용할 수 있다
- 코틀린 람다 안에서는 파이널 변수가 아닌 변수에 접근할 수 있다는 점
- 람다 안에서 바깥의 변수를 변경 가능
- 람다 안에서 사용하는 외부 변수를 `람다가 포획한 변수`라고 부른다
- 기본적으로 함수 안에 정의된 로컬 변수의 생명주기는 함수가 반환되면 끝난다.
  하지만 어떤 함수가 자신의 로컬 변수를 포획한 람다를 반환하거나 다른 변수에 저장한다면 로컬 변수의 생명주기와 함수의 생명주기가 달라질 수 있다

- 파이널이 아닌 변수를 포획한 경우에는 변수를 특별한 래퍼로 감싸서 나중에 변경하거나 읽을 수 있게 한 다음, 래퍼에 대한 참조를 람다 코드와 함께 저장한다
- 비동기적으로 실행되는 코드로 활용하는 경우 함수 호출이 끝난 다음에 로컬 변수가 변경될 수도 있다




5.1.5 멤버 참조


*/

class ExampleCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val button: Button? = null
        button?.setOnClickListener { }
    }
}


fun main() {
    data class Person(val name: String, val age: Int)

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { it.age })
    people.maxBy(Person::age)

    // 5.1.3
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

    people.maxBy({ p: Person -> p.age })
    people.maxBy() { p: Person -> p.age }
    people.maxBy { p: Person -> p.age }
    people.maxBy { p -> p.age }
    people.maxBy { it.age }

    // 변수에 저장
    val getAge = { p: Person -> p.age }
    people.maxBy(getAge)

    // 맨 마지막 람다의 결과 값
    val sum2 = { x: Int, y : Int ->
        println("Computing...")
        x+y
    }
    println(sum2(1,2))

}