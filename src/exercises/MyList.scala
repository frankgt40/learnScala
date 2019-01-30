/*
 * Copyright (c) 2019. reserved by frankgt40
 */

package exercises

abstract class MyList {

  /*
      head = first element of the list
      tail = remainder of the list
      isEmpty = is this list empty
      add(int) => new list with this element adde
      toString => a string representation of the list
   */
  def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(n: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
//  override def toString: String = {
//    def toStringUtil(currList: MyList, acc: String): String = {
//      if (isEmpty()) acc
//      else toStringUtil(tail(), head()+","+acc)
//    }
//    toStringUtil(this, "")
//  }
}

object Empty extends MyList {
  override def head() = throw new NoSuchElementException

  override def tail() = throw new NoSuchElementException

  override def isEmpty() = true

  override def add(n: Int) = new Cons(n, Empty)

  override def printElements = ""
}
class Cons(h: Int, t: MyList) extends MyList {
  override def head() = h

  override def tail() = t

  override def isEmpty() = false

  override def add(n: Int) = new Cons(n, this)

  override def printElements = {
    if (t.isEmpty()) "" + h
    else h + "->" + t.printElements
  }
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.toString)
}
object Test {
  // Implemented by Frankgt40-start
  class MyLinkedList(val value: Int = 0, val next: MyLinkedList = null) extends MyList {
    override def head(): Int = value

    override def tail(): MyLinkedList = if (isEmpty()) null else next

    override def isEmpty(): Boolean = next == null

    override def add(n: Int): MyLinkedList = new MyLinkedList(n, this)

    override def toString: String = {
      def toStringUtil(currList: MyLinkedList, acc: String): String = {
        if (currList isEmpty) acc
        else if (acc isEmpty) toStringUtil(currList.tail(), currList.head().toString)
        else toStringUtil(currList.tail(), acc + "->" + currList.head())
      }
      toStringUtil(this, "")
    }

    override def printElements: String = ???
  }
  val list: MyList = new MyLinkedList
  val list2 = list.add(1)
  val list3 = list2.add(2)
  val list4 = list3.add(3)
  println(list4.toString)
  // Implemented by Frankgt40-end
}
