/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object Objects {
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  def main(args: Array[String]): Unit = {
    object Person { // type _ its only instance
      // "static"/"class" - level functionality
      val N_EYES = 2

      def canFly: Boolean = true

      // factory method
      def from(mother: Person, father: Person): Person = new Person("Bobbie")

      def apply(mother: Person, father: Person): Person = new Person("Bobbie")
    }
    class Person(val name: String) {
      // instance-level functionality
    }
    // COMPANIONS

    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    val bobbie = Person.from(mary, john)
    val bobbie2 = Person(mary, john)

    // Scala Applications = Scala object with
    // def main(args: Array[String]): Unit
  }
}
