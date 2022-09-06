package `kotlin-in-action`.playground

import java.lang.IllegalArgumentException

fun main() {

//    strLen(null)
//    strLenSafe(null)

    printAllCaps("null")

    // 알파벡으로 이뤄진 배열 만들기

    val letters = Array<String>(26) { i -> ('a' + 1).toString() }
    letters.joinToString("")

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
}

fun strLen(s: String) = s.length

fun strLenSafe(s: String?) = s?.length

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase() // toUpperCase
    println("allCaps = $allCaps")
}

fun foo(s: String) {
    val t: String = s ?: ""
    val length = t?.let { t.length }
    println("length = $length")
}

class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService
}

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}



