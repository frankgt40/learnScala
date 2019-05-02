/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part3fp

object HOFsCurries extends  App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // higher order function (HOF)

  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = f(f(...(x))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))


  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))

  // very important!!!!!!
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // function with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFomat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFomat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyList
      - foreach method A=>Unit
        [1,2,3].foreach(x => println(x))
      - sort function ((A,A) => Int) => MyList
        [1,2,3].sort((x,y_ => y - x) => [3,2,1]
      - zipWith (list, (A, A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x * y)=>[1*4, 2*5, 3*6]=>[4, 10, 18]
      - fold(start) (function) => a value
        [1,2,3].fold(0)(x+y) = 6
     2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
        fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
     3. compose(f, g) => x => f(g(x))
        andThen(f, g) => x => g(f(x))

   */

  // 2
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = { x =>  y: Int => f(y, x) }
  println(s"toCurry: ${toCurry(_+_)(1)(2)}")
  def fromCurry(f: (Int=>Int=>Int)): ((Int, Int) => Int) = (x, y) => f(x)(y)
  println(s"fromCurry: ${fromCurry(x => y => x + y)(1, 2)}")

  // 3
  def compose[A, B, T](f: A => B, g: T => A): T=>B = (x: T) => f(g(x))
  def andThen[A, B, T](f: A => B, g: B => T): A => T = (x: A) => g(f(x))
}
