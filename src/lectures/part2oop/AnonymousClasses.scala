/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object AnonymousClasses {
  abstract class Animal {
    def eat: Unit
  }
  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahahaha")
  }
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

}

object ListTest extends App {
  /*
      1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
      2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
      3. MyList:
          - map(transformer) => MyList
          - filter(predicate) => MyList
          - flatMap(transformer from A to MyList[B]) => MyList[B]

          class EvenPredicat extends MyPredicate[Int]
          class StringToIntTransformer extends MyTransformer[String, Int]

          [1,2,3].map(n * 2) = [2, 4. 6]
          [1, 2, 3, 4].filter(n % 2) = [2, 4]
          [1, 2, 3]flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   */
  trait MyPredicate[-T] {
    def test(e: T): Boolean
  }
  trait MyTransformer[-A, B] {
    def transform(e: A): B
  }
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](e: B): MyList[B]
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    override def toString: String = s"[ $printElements ]"
    def printElements[A]: String
  }
  object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def add[B >: Nothing](e: B): MyList[B] = new Cons[B](e, this)
    override def printElements[Nothing]: String = ""
    override def map[B](transformer: MyTransformer[Nothing, B]): MyList[Nothing] = Empty
    override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def ++[B >: Nothing] (list: MyList[B]): MyList[B] = list
  }
  class Cons[+A](val h: A, val t: MyList[A]) extends MyList[A] {
    override def head: A = h
    override def tail: MyList[A] = t
    override def isEmpty: Boolean = false
    override def add[B >: A](e: B): MyList[B] = new Cons[B](e, this)
    override def printElements[A]: String =
      if (t.isEmpty) h.toString
      else h.toString + "->" + t.printElements
    override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))
    override def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ t.flatMap(transformer)
    /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3. 4. 5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
     */
    override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  }

  val listOfInt = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString = new Cons("hello", new Cons(", ", new Cons("world!", Empty)))
  println(listOfInt.toString)
  println(listOfString)

  println(listOfInt.map(new MyTransformer[Int, Int] {
    override def transform(e: Int): Int = e * 3
  }).toString)

  println(listOfInt.map(new MyTransformer[Int, String] {
    override def transform(e: Int): String = "[" + e + "]"
  }))

  println(listOfInt.filter(new MyPredicate[Int]{
    override def test(e: Int): Boolean = e % 2 == 0
  }).toString)

  println((listOfInt ++ listOfString).toString)
  println(listOfInt.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(e: Int): MyList[Int] = new Cons(e, new Cons(e + 1, Empty))
  }).toString)

}