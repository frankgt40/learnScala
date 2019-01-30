/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase)
  println(str.length())

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // s-interpolators

  // f-interpolators
  println(f"Speed is ${12345.6789f}%10.2f")

  // raw-interpolators
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
