package `kotlin-in-action`.playground

fun main() {
    val people = listOf(Person3("Alice", 30), Person3("Bob", 31))
    println(findTheOldest(people))
    println(people.maxBy { it.age }) // it이 그 인자를 가르킨다.
    println(people.maxBy(Person3::age)) // it이 그 인자를 가르킨다.

    people.joinToString(
        separator = " ",
        transform = { p: Person3 -> p.name }
    )
    people.joinToString(separator = " ") { p: Person3 -> p.name }
    people.joinToString(separator = " ") { p -> p.name }
    people.joinToString(separator = " ") { it.name }

    val isTrue = people.all { person -> person.age > 10 }
    val isTrue2 = people.any { person -> person.age > 10 }
    val count = people.count { person -> person.age > 10 }
    people.groupBy { people -> people.age }

    listOf(Book("d", listOf("A", "B"))).flatMap { it.authors }.toSet()

    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it)"); it * it }
        .filter { print("filter($it)"); it % 2 == 0 }
        .toList() //    map(1)filter(1)map(2)filter(4)map(3)filter(9)map(4)filter(16)

    println()

    listOf(1, 2, 3, 4)
        .map { print("map($it)"); it * it }
        .filter { print("filter($it)"); it % 2 == 0 }
        .toList()//    map(1)map(2)map(3)map(4)filter(1)filter(4)filter(9)filter(16)

    alphabet2()

}

class Book(val title: String, val authors: List<String>)
data class Person3(val name: String, val age: Int)

fun findTheOldest(people: List<Person3>) {
    var maxAge = 0
    var theOldest: Person3? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun salute() = println("Salute!")
// >>> run(::salute)


// sendEmail 에게 작업을 위임한다.
val action = { person: Person3, message: String -> sendEmail(person, message) }

fun sendEmail(person: Person3, message: String) {

}

val nextAction = ::sendEmail

fun createAllDoneRunnable(): Runnable {
    return Runnable { println("All done!") }
}

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun alphabet2(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        this.append("\nNow I know the alphabet!")
        return this.toString()
    }
}

fun alphabet3() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}


fun alphabet4() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()


fun alphabet5() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()






