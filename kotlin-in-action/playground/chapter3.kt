import `kotlin-in-action`.playground.joinToString

fun main() {
    joinToStringSample()

}

fun collectionGenerate() {
    // collection generate
    val set = hashSetOf(1, 3, 5)
    val list = arrayListOf(1, 7, 22)
    val map = hashMapOf(1 to "one", 7 to "seven")
}

fun joinToStringSample() {
    val list = arrayListOf(1, 7, 22)
    val message = joinToString(list, ";", "(", ")")
    println(message)

    println(
        joinToString(collection = list, separator = ";", prefix = "(", postfix = ")")
    )

}
