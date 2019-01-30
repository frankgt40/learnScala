/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object Inheritance extends App {
  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def  eat = println("nomnom")
  }
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }
  val cat = new Cat
  cat.crunch

  // constructors
  class Person(ame: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
//    override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution: polymorphism
  val unknownAnimal: Animal = new Dog("K-9")
  unknownAnimal.eat


  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1.  use final on member
  // 2. use final on the entire class
  // 3. seal the class = extend classes in THIS FILE, prevent extensions in other files
}
