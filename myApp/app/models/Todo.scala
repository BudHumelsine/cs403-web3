package models

import scala.collection.mutable.ListBuffer

case class TodoItem(title : String, priority : Int)

object Todo {   
  var list = ListBuffer(
      TodoItem("SS307 homework", 4),
      TodoItem("CS403 Milestone 5", 1), 
      TodoItem("Meet with DAC", 2), 
      TodoItem("CS385 homework", 1) 
      )
  
  def getItems = list.toList
  
  def delete(title : String) = {
    for (item <- list) {
      if (item.title == title) 
        list -= item
    }
  }
  
  def add(item : TodoItem) {
    list += item
    println(item + " added")
  }
    
}