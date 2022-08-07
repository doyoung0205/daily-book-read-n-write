package `kotlin-in-action`.playground

fun main() {
    val button = Button()
    button.click()
    button.setFocus(true)
    button.showOff()
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