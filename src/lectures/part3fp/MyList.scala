/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package lectures.part3fp

import scala.annotation.tailrec

object MyList extends App {
  val listOfInt = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString = new Cons("hello", new Cons(", ", new Cons("world!", Empty)))
  println(listOfInt.toString)

  listOfInt.foreach(x => println(x))
  println(listOfString)

  println(listOfInt.map(_ * 3).toString)

  println(listOfInt.map(e =>s"[${e}]" ))

  println(listOfInt.filter(_ % 2 == 0).toString)

  println((listOfInt ++ listOfString).toString)
  println(listOfInt.flatMap(e => new Cons(e, new Cons(e + 1, Empty))).toString)

  val list = new Cons(2, new Cons(9, new Cons(-1, new Cons(2, Empty))))
  println("list.sort: " + list.sort((x, y) => x - y))

  val list2 = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(list.zipWith(list2, (x: Int, y: Int) => x * y))
  println(listOfInt.zipWith[String, String](listOfString, _ + "-" + _))
  println(listOfInt.fold(0)(_+_))
}



abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](e: B): MyList[B]

  def map[B](transformer: A => B): MyList[B]
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
  */
  def foreach(f: A => Unit): Unit

  def sort(f: (A,A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], f: (A,B) => C ): MyList[C]

  def fold[B >: A](start: B): ((A, B) => B) => B


  def filter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

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

  override def map[B](transformer: Nothing => B): MyList[Nothing] = Empty

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  override def fold[B >: Nothing](start: B): ((Nothing, B) => B) => B = { f => start}
}

class Cons[+A](val h: A, val t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](e: B): MyList[B] = new Cons[B](e, this)

  override def printElements[A]: String =
    if (t.isEmpty) h.toString
    else h.toString + "->" + t.printElements

  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  /*
  [1,2] ++ [3,4,5]
  = new Cons(1, [2] ++ [3. 4. 5])
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(f: (A, A) => Int): MyList[A] = {
//    // NOT tail recursive
//    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
//      if (sortedList.isEmpty) new Cons(x, Empty)
//      else if (f(x, sortedList.head) < 0) new Cons(x, sortedList)
//      else new Cons(sortedList.head, insert(x, sortedList.tail))
//    }
//    val sortedTail = t.sort(f)
//    insert(h, sortedTail)
    @tailrec
    def insert(left: MyList[A], x: A, right: MyList[A]): MyList[A] = {
      if (right.isEmpty) left ++ new Cons(x, Empty)
      else if (f(x, right.head) <= 0) left ++ new Cons(x, right)
      else insert(left ++ new Cons(right.head, Empty), x, right.tail)
    }
    val sortedTail = t.sort(f)
    insert(Empty, h, sortedTail)
  }

  override def toString: String = super.toString

  override def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(f(h, list.head), t.zipWith(list.tail, f))
  }

  override def fold[B >: A](start: B): ((A, B) => B) => B = {
    f => {
      f(head, t.fold(start)(f))
    }
  }
}
