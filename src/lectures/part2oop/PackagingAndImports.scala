/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

//import playground.Cinderella

object PackagingAndImports extends App {
  // package member are accessible by their simple name
  val writer = new Writer("Daniel", "RockIt", "2018")

  // import the package
//  val princess = new playground.Cinderella // fully qualified name
  val princess = new Princess // fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  val sqlDate = new java.sql.Date(2018, 5, 5)
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
