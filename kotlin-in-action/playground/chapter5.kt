package `kotlin-in-action`.playground

import java.util.StringJoiner

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
        .toList()
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

