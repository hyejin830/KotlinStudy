package com.example.kotlinstudyproject.chapter4

import android.content.Context
import android.util.AttributeSet

/*

주 생성자 : 클래스를 초기화할 때 주로 사용하는 간략한 생성자로, 클래스 본문 밖에서 정의한
부 생성자 : 클래스 몬문 안에서 정의한다
초기화 블록을 통해 초기화 로직을 추가할 수 있다
 */

/*
 1 클래스 초기화 : 주 생성자와 초기화 블록

 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드를 주 생성자라고 부른다
 주 생성자는 생성자 파라미터를 지정하고, 그 생성자 파라미터에 의해 초기화되는 프로퍼티를 정의하는 두 가지 목적에 쓰인다

 constructor 키워드 : 주 생성자나 부 생성자 정의를 시작할 때 사용
 init 키워드 : 초기화 블록을 시작, 주 생성자와 함께 사용


*/

class User(val nickname: String)

// 밑줄(_)은 프로퍼티와 생성자 파라미터를 구분
class User2 constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

class User10  {

    init {
     val string: String = "dd"
        }
}
class User3(_nickname: String) {
    val nickname: String = _nickname
}

/*
User = User2 = User3

*/

class User4(val nickname: String, val isSubscribed: Boolean = true)


/*

 클래스에 기반 클래스가 있다면 주 생성자에서 기반 클래스의 생성자를 호출해야 할 필요가 있다
 기반 클래스를 초기화하려면 기반 클래스 이름 뒤에 괄호를 치고 생성자 인자를 넘긴다

*/

open class User5(val nickname: String) {}

class TwitterUser(nickname: String) : User5(nickname) {}

/*
 클래스를 정의할 때 별도의 생성자를 정의하지 않으면 컴파일러가 자동으로 아무 일도 하지 않는 인자가 없는 디폴트 생성자를 만들어준다
 이 규칙으로 인해 기반 클래스 이름 뒤에는 꼭 빈 괄호가 들어간다 ( 물론 생성자 인자가 있다면 괄호 안에 인자가 들어간다)

 인터페이스인 경우 생성자가 없기 때문에 어떤 클래스가 인터페이스를 구현하는 경우, 그 클래스의 상위 클래스 목록에 있는 인터페이스 이름 뒤에는 아무 괄호도 없다
 클래스 정의에 있는 상위 클래스 및 인터페이스 목록에서 이름 뒤에 괄호가 붙었는지 살펴보면 쉽게 기반 클래스와 인터페이스를 구별할수 있다

 */
open class Example

class Test : Example()

/*
 클래스를 외부에서 인스턴스화 하지 못하게 막고 싶다면 모든 생성자를 private하게 만들면 된다
*/

class Secretive private constructor() // 이 클래스의 주 생성자는 비공개이다

/*

 2 부 생성자 : 상위 클래스를 다른 방식으로 초기화

 constructor 키워드로 시작
 super() 키워드를 통해 자신에 대응하는 상위 클래스 생성자를 호출한다
 this()를 통해 클래스 자신의 다른 생성자를 호출할 수 있다
 필요한 주된 이유는 자바 상호운용성이다.

*/

open class View {
    constructor(ctx: Context)
    constructor(ctx: Context, attr: AttributeSet)
}

class MyButton : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr)
}

/*
class MyButton2 : View {

    constructor(ctx: Context) : this(ctx, MY_STYLE)
    constructor(ctx:Context, attr: AttributeSet):super(ctx,attr)
}
*/

/*
 3 인터페이스에 선언된 프로퍼티 구현
  인터페이스에 추상 프로퍼티 선언을 넣을 수 있다
*/

// UserInterface 를 구현하는 클래스가 nickname의 값을 얻을 수 있는 방법을 제공해야 한다
interface UserInterface {
    val nickname: String
}

/*

인터페이스에 있는 프로퍼티 선언에는 뒷받침하는 필드가 게터 등의 정보가 들어있지 않다
인터페이스는 아무 상태도 포함할 수 없으므로 상태를 저장할 필요가 있다면 인터페이스를 구현한 하위 클래스에서 상태 저장을 위한 프로퍼티 등을 만들어야 한다

*/

class PrivateUser(override val nickname: String) : UserInterface

class SubscribingUser(val email: String) : UserInterface {
    override val nickname: String
        get() = email.substringBefore('@')
}

/*
class FacebookUser(val accoundId : Int) : UserInterface{
    override val nickname: String = getFa
}
*/

/*
 4 게터와 세터에서 뒷받침하는 필드에 접근

어떤 값을 저장하되 그 값을 변경하거나 읽을 때마다 정해진 로직을 실행하는 유형의 프로퍼티를 만드는 방법

field : 특별한 식별자, 뒷받침하는 필드에 접근할 수 있다.


*/

// 커스텀 세터를 정의해서 추가 로직을 실행
class User6(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println(
                """
            Address was changed for $name :
            "$field" -> "$value"""".trimIndent()
            )
            field = value
        }
}

/*

 5 접근자의 가시성 변경
 기본적으로 프로퍼티의 가시성과 같다
 원한다면 get이나 set 앞에 가시성 변경자를 추가해서 접근자의 가시성을 변경할 수 있다

*/

class LengthCounter {
    var counter: Int = 0
        private set // 이 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 없다

    fun addWord(word: String) {
        counter += word.length
    }
}


fun main() {
    val hyun = User4("현석") // isSubscribed는 디폴트 값이 쓰인다
    println(hyun.isSubscribed)

    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)

    val user = User6("Alice")
    user.address = "Incheon"

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)

}
