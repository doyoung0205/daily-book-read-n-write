import `kotlin-in-action`.playground.joinToString
import java.lang.IllegalArgumentException

fun main() {
//    joinToStringSample()
//    lastCharSample()
//    threeStringSample()


}

class User(val id: Int, val name: String, val address: String)

fun saveUserV1(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }

    // user database 에 저장
}

fun saveUserV2(user: User) {
    fun validate(user: User, value: String, filedName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty ${filedName}")
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")

    // user database 에 저장
}


fun saveUserV3(user: User) {
    fun validate(value: String, filedName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty ${filedName}") // 바깥 함수의 파라미터에 접근할 수 있다.
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")

    // user database 에 저장
}

fun saveUserV4(user: User) {
    user.validateBeforeSave()

    // user database 에 저장
}

fun User.validateBeforeSave() {
    fun validate(value: String, filedName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${id}: empty ${filedName}") // 바깥 함수의 파라미터에 접근할 수 있다.
        }
    }
    validate(name, "Name")
    validate(address, "Address")
    // user database 에 저장
}


private fun lastCharSample() {
    val sb = StringBuilder("doyoun")
    sb.lastChar = 'g'
    println(sb) // doyoug
}

private fun threeStringSample() {
    val logo = """ | //
              .| //
              .|/ \"""
    println(logo.trimMargin())
    val path = """C:\Users\kotlin-book""" // C:\\Users\\kotlin-book
    // 단 $를 넣으려면 '$' 이렇게 사용해야 한다.
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

/**
 *  joinToString 를 collections 확장함수로 정의
 */
fun <T> Collection<T>.joinToString(
    separator: String = " ",
    prefix: String = "[",
    postfix: String = "]"
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}


var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }


fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = path.substringBeforeLast(".")
    val extension = path.substringAfterLast(".")

    println("Dir: ${directory}, name : ${fileName}, ext:${extension}")
}

// 정규식 사용하기
fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: ${directory}, name : ${fileName}, ext:${extension}")
    }
}


