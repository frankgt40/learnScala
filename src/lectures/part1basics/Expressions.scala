/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part1basics

object Expressions extends App {
  val x = 1+2
  println(x)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)

  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }
  // NEVER WRITE THIS AGAIN

  var aVariable = 1
  // EVERYTHING IN SCALA IS AN EXPRESSION!
  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)

  // side affects: println(), whiles, reassigning

  // Code blocks are expressions
  val aCodeBlock = {
    val y = 2
    val z = y  + 1
    if (z > 2) "hello" else "goodbye"
  }
}
