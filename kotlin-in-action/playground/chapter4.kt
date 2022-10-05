package `kotlin-in-action`.playground

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.lang.IllegalArgumentException
import java.util.Collections
import javax.naming.Context
import javax.swing.text.AttributeSet

fun main() {
//    val button = Button()
//    button.click()
//    button.setFocus(true)
//    button.showOff()

//    println(Client("dory", 1))
    val message = Client("dory", 1) == Client("dory", 1)
    println(message)

    val copy = Client2("dory", 3).copy(name = "dory")

    Payroll.allEmployees.add("dory")
    Payroll.calculateSalary()
}

interface Clickable {
    fun click() // 일반 메서드 선언
    fun showOff() = println("I'm clickable") // default 구현이 있는 메서드
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm focusable")
}

class Button : Clickable, Focusable {
    override fun click() {
        println("I was clicked")
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable {

    fun disable() {
        // 이 함수는 final 이다. 하위 클래스가 이 메서드를 오버라이드 할 수 없다.
    }

    open fun animate() {
        // 이 함수는 열려있다. 하위 클래스에서 이 메서드를 오버라이드해도 된다.
    }

    override fun click() {
        // 이 함수는 (상위 클래스에서 선언된) 열려있는 메서드를 오버라이드 한다.
        // 오버라이드한 메서드는 기본적으로 열려 있다.
        println("rich button click")
    }

//    final override fun click() {
//      // 오버라이드 금지하기 위해선 final 을 붙여주면 된다.
//    }

}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

//fun TalkativeButton.giveSpeech() { // 'public' member exposes its 'internal' receiver type TalkativeButton
//    yell(); // private
//    whisper(); // proteced
//}


fun eval(e: Expr): Int {
    return when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }
}

//interface Expr
sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()

}


open class User(
    val nickName: String = "doyoung" // defaultt 생성자
)

class TwitterUser(nickName: String) : User(nickName)


open class View {
    constructor(ctx: Context) { // 부 생성자

    }


    constructor(ctx: Context, attr: String) { // 부 생성자

    }

}

class MyButton : View {
    constructor(ctx: Context) : this(ctx, "MY_STYLE") { // 이 클래스의 다른 생성자에게 위임한다.
    }

    constructor(ctx: Context, attr: String) : super(ctx, attr) { // 상위 클래스와 생성자 호출
    }
}


interface UserAble {
    val nickname: String
}

interface UserAble2 {
    val email: String
    val nickname: String
        get() = email.substringBefore("@")
}

// 주 생성자 안에 프로퍼티를 직접 선언하는 간결한 구문
class PrivateUser(override val nickname: String) : UserAble

// 커스텀 게터로 프로퍼티를 설정한다.
class SubscribingUser(private val email: String) : UserAble {
    override val nickname: String
        get() = email.substringBefore("@")
}

// 초기화 식으로 nickname 값을 초기화한다.
class FacebookUser(val accountId: Int) : UserAble {
    override val nickname = getFacebookName(accountId)
    private fun getFacebookName(accountId: Int): String {
        return "FacebookName"
    }
}


class User2(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
                Address was changed for $name
                "$field" -> "$value".""".trimIndent() // 뒷 받침하는 필드 값 읽기
            )
            field = value // 뒷받침하는 필드 값 변경하기
        }
}


class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=${name},postalCode=${postalCode})"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Client) return false

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }

    fun copy(
        name: String = this.name,
        postalCode: Int = this.postalCode
    ) = Client(name, postalCode)
}


data class Client2(val name: String, val postalCode: Int)


class DelegatingCollection<T>() : Collection<T> {

    private val innerList = arrayListOf<T>()

    override val size: Int
        get() = innerList.size

    override fun isEmpty(): Boolean {
        return innerList.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return innerList.iterator()
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return innerList.containsAll(elements)
    }

    override fun contains(element: T): Boolean {
        return innerList.contains(element)
    }
}


class DelegatingCollection2<T>(
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList


class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}


object Payroll {
    val allEmployees = arrayListOf<String>()

    fun calculateSalary() {
        for (employee in allEmployees) {
            // ...
        }
    }
}

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}


class User3 private constructor(val nickName: String) {
    companion object {
        fun newSubscribingUser(email: String) =
            User3(email.substringBefore("@"))

        fun newFacebookUser(accountId: Int) =
            User3("facebook$accountId")
    }
}

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
    companion object : JSONFactory<Person> {
        override fun fromJSON(jsonText: String): Person {
            return Person(jsonText)
        }
    }
}


// 비즈니스 모듈

class Person2(val firstName: String, val lastName: String) {
    companion object {
    }
}

// 클라이언트/서버 통신 모듈
fun Person2.Companion.fromJSON(json: String): Person2 {
    // ...
    return Person2("", "")
}

fun something() {
    val listener = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            super.mouseClicked(e)
        }

        override fun mouseEntered(e: MouseEvent?) {
            super.mouseEntered(e)
        }
    }
}
