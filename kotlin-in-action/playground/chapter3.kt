fun main() {
    joinToStringSample()

}

fun collectionGenerate() {
    // collection generate
    val set = hashSetOf(1, 3, 5)
    val list = arrayListOf(1, 7, 22)
    val map = hashMapOf(1 to "one", 7 to "seven")
}


fun <T> joinToString(
    collection: Collection<T>,
    separator: String = " ",
    prefix: String = "[",
    postfix: String = "]"
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun joinToStringSample() {
    val list = arrayListOf(1, 7, 22)
    val message = joinToString(list, ";", "(", ")")
    println(message)

    println(
        joinToString(collection = list, separator = ";", prefix = "(", postfix = ")")
    )

}
