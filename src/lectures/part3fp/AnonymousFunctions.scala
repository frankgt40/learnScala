/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part3fp

object AnonymousFunctions extends App {

//  val doubler = new Function[Int, Int] {
//    override def apply(v1: Int): Int = v1 * 2
//  }
//  val doubler = (x: Int) => x * 2 // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2 // anonymous function (LAMBDA)

  // multiple params in a lambda
//  val adder = (a:Int, b:Int) => a + b
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAddr: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b, each _ represents for one parameter, cannot use _ multiple times for the same parameter

  /*
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */
  // 2.
  val adder2 = (x: Int) => (y: Int) => y + x
  println(adder2(3)(4))

}
