/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  // println(x.length)
  // this will crash with a NPE

  // 1. throwing and catching exceptions

  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch eception
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("no int for you")
    else 42

  val potentialFial = try {
    // code might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43
    case e: NullPointerException => println("caught a NPE exception")
  } finally {
    // code that will get executed  NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFial)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with StackOverflowError
    3. PocketCalculator
        - add(x,y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throw
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
          - MatchCalculationException for division by 0
   */

  // OOME
  //val array = Array.ofDim(Int.MaxValue)

  // SOE
  //def infinite: Int = 1 + infinite
  //val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculatorException extends RuntimeException
  case object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if (Int.MaxValue - x < y) throw new OverflowException
      else if (Int.MinValue - y > x) throw new UnderflowException
      else x + y
    }
    def subtract(x: Int, y: Int): Int =
      if (Int.MinValue + y > x) throw new UnderflowException
      else if (Int.MaxValue + y < x) throw new OverflowException
      else x - y
    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculatorException
      else x / y
    def multiply(x: Int, y: Int): Int =
      if (y >= 0)
        if (Int.MaxValue / y < x) throw new MathCalculatorException
        else x * y
      else
        if (Int.MaxValue / y > x) throw new MathCalculatorException
        else x * y
  }
}
