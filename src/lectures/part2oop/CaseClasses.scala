/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object CaseClasses extends App {
  /*
      equals, hashCode, toString
   */
  case class Person(name: String, age: Int)

  // 1. class parameter are fields
  val jim = new Person("jim", 23)
  jim.name

  // 2. sensible toString
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented OOTB (out of the box)
  val jim2 = new Person("jim", 23)
  println(jim == jim2)

  // 4. case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 43)

  // 6. case classes are serializable
  // Akka

  // 7. case classes have extractor patterns = CCs can be used in PATTERN MATCHING
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
      Expand MyList = use case classes and case objects
   */
}
