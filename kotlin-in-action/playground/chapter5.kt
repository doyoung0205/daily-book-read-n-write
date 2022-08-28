package `kotlin-in-action`.playground

import joinToString

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

}

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


