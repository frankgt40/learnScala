/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("Hello", 3))
  // when you need loops, use recursion.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int) : Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n - 1)
  }
  println(aBigFunction(3))

  /*
  1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
  2. Factorial function 1 * 2 * 3 * .. * n
  3. A fibonacci function
      f(1) = 1
      f(2) = 1
      f(n) = f(n - 1) + f(n - 2)
  4. Tests if a number is a prime.
   */
  // 1
  def greeting(name: String, age: Int): Unit = println(s"Hi, my name is $name and I am $age years old.")
  println(greeting("Cheng Cai", 30))
  // 2
  def factorial(n: Int): Int = {
    if (n == 1) 1
    else factorial(n-1) * n
  }
  println(factorial(5))
  // 3
  def fibonacci(n: Int): Int = {
    if (n == 1) 1
    else if (n == 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(3))
  // 4
  def isPrime(i: Int): Boolean = {
    def isPrimeUtil(i: Int, tmp: Int): Boolean = {
      if (tmp < i / 2) i % tmp != 0
      else (i % tmp != 0) & isPrimeUtil(i, tmp-1)
    }
    isPrimeUtil(i, i-1)
  }
  println(isPrime(7))
  println(isPrime(8))

  def isPrime2(i: Int): Boolean = {
    def isPrimeUtil(tmp: Int): Boolean = {
      if (tmp < i/2) i % tmp != 0
      else i % tmp != 0 && isPrimeUtil(tmp - 1)
    }
    isPrimeUtil(i-1)
  }
  println(isPrime2(17))
  println(isPrime2(20))
}
