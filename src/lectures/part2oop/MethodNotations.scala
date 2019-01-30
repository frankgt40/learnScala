/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"
    def isAlive(): Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    //1
    def +(namePost: String): Person = new Person(s"${this.name} ($namePost)", this.favoriteMovie)
    //2
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)
    //3
    def learns(target: String): String = s"$name learns $target"
    def learnsScala: String = this learns "Scala"
    //4
    def apply(n: Int = 1): String = s"$name watched $favoriteMovie $n times"


  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation, only work with one parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))
  println(1.+(2))

  // ALL OPERATORS ARE METHODS


  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive())
  println(mary isAlive)

  // apply: break the barrier between functional and oo programming languages
  println(mary.apply())
  println(mary())  // equivalent

  /*
  1. Overload the + operator
    mary + "the rockstar" => new person "Mary (the rockstar)"
  2. Add and age to the Person class
    Add a unary + operator => new person with the age + 1
    +mary => mary with the age incremented
  3. Add a "learns" method in the Person class => "Mary learns Scala"
   Add a learnsScala method, calls learns method with "Scala"
   Use is in postfix notation

  4. Overload the apply method
  mary.apply(2) => "Mary watched Inception 2 times"
   */

  println((mary + "the Rockstar")())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(10))
}
