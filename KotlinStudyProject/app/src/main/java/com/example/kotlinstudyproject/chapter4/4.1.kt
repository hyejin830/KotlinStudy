package com.example.kotlinstudyproject.chapter4

/*
 클래스를 data로 선언하면 컴파일러가 일부 표준 메소드를 생성해준다
 코틀린 언어가 제공하는 위임을 사용하면 위임을 처리하기 위한 준비 메소드를 직접 작성할 필요가 없다
 object 키워드 : 클래스와 인스턴스를 동시에 선언하면서 만들 때 쓰는

 1 코틀린 인터페이스
  - 추상 메소드뿐 아니라 구현이 있는 메소드도 정의할 수 있다
  - 아무런 상태(필드)도 들어갈 수 없다
  - 인터페이스 구현 키워드 -> :
  - override 변경자 : 자바와 달리 꼭 사용해야 한다
 */


interface Clickable {

    val str : String
        get() = "hyejin"

    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {

    override fun click() = println("I was clicked!")



    // Clickable과 Focusable의 showOff 중 어느 쪽도 선택되지 않는다
    // 두 상위 인터페이스에 정의된 showOff 구현을 대체할 오버라이딩 메소드를 직접 제공해야 한다
    // super로 상위 타입을 호출하고, 타입을 지정할 때는 꺽쇠 괄호를 이용하여 기반 타입 이름을 지정한다

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

/*
 2 open, final, abstract 변경자 : 기본적으로 final

  취약한 기반 클래스 : 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 바뀔 수도 있다는 면에서 기반 클래스
  자바에서는 이 문제를 해결하기 위해 하위 클래스에서 오버라이드가 의도된 클래스와 메소드가 아니라면 모두 final로 만들어야 한다

  코틀린의 클래스와 메소드는 기본적으로 final이다
  어떤 클래스의 상속을 허용하려면 `클래스` 앞에 open 변경자를 붙여야 한다
  오버라이드를 허용하고 싶은 `메소드나 프로퍼티` 앞에도 open 변경자를 붙여야 한다


*/

open class RichButton : Clickable { // 이 클래스는 열려 있고 다른 클래스가 이 클래스를 상속할 수 있다

    // 이 함수는 파이널이며 하위 클래스가 이 메소드를 오버라이드 할 수 없다
    fun disable() {}

    // 이 함수는 열려있으며, 하위 클래스에서 이 메소드를 오버라이드 해도 된다
    open fun animate() {}

    // 오버라이드한 메소드는 기본적으로 열려있다 , 그러나 오버라이드 하지 못하게 하려면 final을 명시해야 한다
    final override fun click() {}

}

class PracticeOpenClass : RichButton() {
    override fun animate() {
        super.animate()
        println("상속했다")
    }
}

/*
 코틀린에서도 클래스를 abstract로 선언할 수 있다
 abstract로 선언한 추상 클래스는 인스턴스화 할 수 없다
 추상 클래스에는 구현이 없는 추상 멤버가 있기 때문에 `하위 클래스`에서 `그 추상 멤버를 오버라이드해야만` 하는게 보통이다

*/

// 이 클래스는 추상 클래스이다. 이 클래스는 인스턴스를 만들 수 없다
abstract class Animated {

    constructor()


    // 이 함수는 추상 함수다. 이 함수에는 구현이 없으며 하위 클래스에서는 이 함수를 반드시 오버라이드 해야한다
    abstract fun animate()

    // 추상 클래스에 속했더라도 비 추상 함수는 기본적으로 파이널이지만 원한다면 open으로 오버라이드를 허용할 수 있다
    open fun stopAnimating() {

    }

    fun animateTwice() {

    }
}

/*
 인터페이스 멤버의 경우 final, open, abstract를 사용하지 않는다
 인터페이스 멤버는 항상 열려 있으며 final로 변경할 수 없다
 인터페이스 멤버에게 본문이 없으면 자동으로 추상 멤버가 되지만, 그렇더라도 따로 멤버 선언 앞에 abstract를 덧붙일 필요가 없다
*/

/*

 클래스 내에서 상속 제어 변경자의 의미

 fianl : 오버라이드할 수 없음, 클래스 멤버의 기본 변경자
 open : 오버라이드 할 수 있음, 반드시 open을 명시해야 오버라이드 할 수 있다
 abstract : 반드시 오버라이드 해야 함, 추상 클래스의 멤버에만 이 변경자를 붙일 수 있다. 추상 멤버에는 구현이 있으면 안된다
 override : 상위 클래스나 상위 인스턴스의 멤버를 오버라이드 하는 중, 오버라이드하는 멤버는 기본적으로 열려있다. 하위 클래스의 오버라이드를 금지하려면 final을 명시해야 한다

*/

/*
 3 가시성 변경자 : 기본적으로 공개
  코드 기반에 있는 선언에 대한 클래스 외부 접근을 제어한다

  public : 모든 곳에서 볼 수 있다.
  internal : 같은 모듈 안에서만 볼 수 있다
  protected : 하위 클래스 안에서만 볼 수 있다
  private : 같은 클래스 안에서만 볼 수 있다

  클래스를 확장한 함수는 그 클래스의 private나 protected 멤버에 접근할 수 없다
*/

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's a talk!")
}

/*fun TalkativeButton.giveSpeech(){
    yell()
    whisper()
}*/


/*

 4 내부 클래스와 중첩 클래스 : 기본적으로 중첩 클래스

 코틀린의 중첩 클래스는 명시적으로 요청하지 않는 한 바깥쪽 클래스 인스턴스에 대한 접근 권한이 없다는 특징이 있다
 코틀린 중첩 클래스에 아무런 변경자가 붙지 않으면 자바 static 중첩 클래스와 같다
 이를 내부 클래스로 변경해서 바깥쪽 클래스에 대한 참조를 포함하게 만들고 싶다면 inner 변경자를 붙여야 한다

 내부 클래스 inner 안에서 바깥쪽 클래스를 참조하려면 this@클래스이름 라고 써야 한다
*/

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

/*
 5 봉인된 클래스 : 클래스 계층 정의 시 계층 확장 제한

 sealed 변경자를 붙이면 그 상위 클래스를 상속한 하위 클래스 정의를 제한할 수 있다
 sealed 클래스의 하위 클래스를 정의할 때는 반드시 상위 클래스 안에 중첩시켜야 한다
 sealed 클래스는 자동으로 open 이다

*/

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }

fun main() {

    val button = Button()
    button.click()
    button.showOff()
    button.setFocus(true)

    val button2 = PracticeOpenClass()
    button2.disable()


}
