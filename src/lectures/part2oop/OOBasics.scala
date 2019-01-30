/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Frank", 30)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet

  val author = new Writer("Frank", "Cai", "1989")
  val author2 = new Writer("Frank", "Cai", "1989")
  val novel = new Novel("Call-to-reference", "2018", author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(author2))
}


class Person(name: String, val age: Int = 0) { // constructor
  // class parameters are not fields
  // body
  val x = 2 // var and val inside the block are fields

  println(1+3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors (or overloading constructors)
  def this(name: String) = this(name, 0) // calls the primary constructor
}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname
  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
 */
class Writer(val firstName: String, surname: String, val year: String) {
  def fullName(): String = firstName + " " + surname
}
class Novel(name: String, year: String, author: Writer) {
  def authorAge(): Int = 2019 - author.year.toInt
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYearRelease: String): Novel = {
    new Novel(this.name, newYearRelease, this.author)
  }
}
/*
Counter class
  - receives an int value
  - method current count
  - method to increment/decrement => new Counter
  - overload inc/dec to receive an amount
 */
class Counter(val i: Int) {
  def currentCount(): Int = this.i
  def inc: Counter = new Counter(this.i + 1) // immutability
  def dec: Counter = new Counter(this.i - 1)
  def inc(amount: Int): Counter = new Counter(this.i + amount)
  def dec(amount: Int): Counter = new Counter(this.i - amount)
}