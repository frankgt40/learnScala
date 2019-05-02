/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part3fp

object WhatsAFunction extends  App {

  // DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A, B, R] === (A,B)=>R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate an MyTransformer into function types
    3. define a function wich takes an int and returns another function which takes an int and returns a int
      - what's the type of this function
      - how to do it
   */
  // 1
  val f1: ((String, String)=>String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  //2

  // 3. curried function!!!
  val f3: ((Int) => ((Int)=>Int)) = new Function1[Int, ((Int)=>Int)] {
    override def apply(v1: Int): Int => Int = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v2 + v1
    }
  }
  println(f3(1)(2)) // Curried function

}


trait MyFunction[A, B] {
  def apply(element: A): B
}