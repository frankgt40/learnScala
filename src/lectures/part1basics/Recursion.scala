/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  //println(factorial(100000)) // stackoverflowerror

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x-1, x*accumulator) // TAIL RECURSION = use recursive call as the LAST expression
    }
    factHelper(n, 1)
  }
  println(anotherFactorial(1000))
  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  /*
  1. Concatenate a string n times
  2. IsPrime function tail recursive
  3. Fibonacci function, tail recursive
   */

  // 1
  def concate(str: String, n: Int): String = {
    @tailrec
    def concateUtil(i: Int, accumulator: String): String = {
      if (i >= n) accumulator
      else concateUtil(i+1, str+accumulator)
    }
    concateUtil(0, "")
  }
  println(concate("ABC", 3))
  // 2
  def isPrime(n: Int): Boolean = {
    def isPrimeUtil(i: Int, accumulator: Boolean): Boolean = {
      if (!accumulator) false
      else if (i < n/2) accumulator
      else isPrimeUtil(i-1, n % i != 0 && accumulator)
    }
    isPrimeUtil(n-1, true)
  }
  println(isPrime(2003))
  println(isPrime(4000))

  //3
  def fibonacciSum(n: Int): Int = {
    def fibUtil(i: Int, pre: Int, prepre: Int, accumulator: Int): Int = {
      if (i >= n) accumulator
      else fibUtil(i+1, pre+prepre, pre, accumulator+pre+prepre)
    }
    fibUtil(1, 1, 0, 1)
  }
  println(fibonacciSum(1))
  println(fibonacciSum(2))
  println(fibonacciSum(3))
  println(fibonacciSum(4))
}
