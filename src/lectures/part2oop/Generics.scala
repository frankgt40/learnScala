/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object Generics extends App {

  class MyList1[A] {
    // use the type A
  }

  val listOfIntegers = new MyList1[Int]
  val listOfStrings = new MyList1[String]

  class MyMap[Key, Value]
  trait MyList2[A]


  // generic methods
  object MyList1 {
    def empty[A]: MyList1[A] = ???
  }

  //val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1.yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] {
    def add[B >: A](e: B): CovariantList[B] = ???
  }
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION

  // 2. No = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // upper bound
  class Cage[A <: Animal] (animal: A) // A is subtype of Animal
  val cage = new Cage(new Dog)
//  val newCage = new Cage(new Cage)

  // expand MyList to be generic
  abstract class MyList[+A] {
    def head(): A
    def tail(): MyList[A]
    def isEmpty: Boolean
    def add[B >: A](e: B): MyList[B]
    def printElements: String
    override def toString: String = s"[ ${printElements} ]"
  }
  object Empty extends MyList[Nothing] {
    override def head(): Nothing = throw new NoSuchElementException
    override def tail(): MyList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, this)
    override def printElements: String = ""
  }
  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    override def head(): A = h
    override def tail(): MyList[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](e: B): MyList[B] = new Cons(e, this)
    override def printElements: String =
      if (t.isEmpty) "" + h.toString
      else h.toString + "->" + t.printElements
  }
  val listAny: MyList[Any] = new Cons("Hello", new Cons(", ", new Cons("world!", new Cons[Int](123, Empty))))
  println(listAny)
}
