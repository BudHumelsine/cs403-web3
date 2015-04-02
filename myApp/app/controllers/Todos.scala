package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

object TodoList extends Controller {

   val formMapping = mapping(
    "title" -> nonEmptyText,
    "priority" -> number
  )(TodoItem.apply)(TodoItem.unapply)
  
  val todoItemForm : Form[TodoItem] = Form(formMapping)
  
  def items = Action {
    val list = Todo.getItems
    Ok(views.html.items(list, todoItemForm))
  }
  
  def deleteTask(title : String) = Action {    
    Todo.delete(title)
    Redirect(routes.TodoList.items)
  }
  
  def add = Action { implicit request =>
    todoItemForm.bindFromRequest.fold(
      errors => BadRequest(views.html.items(Todo.getItems, errors)),
      item => {
        Todo.add(item)
        Redirect(routes.TodoList.items)
      }
    )
  }
  

  
}