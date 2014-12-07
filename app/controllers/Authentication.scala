package controllers

import play.api.data.Forms._
import play.api.data._
import play.api.mvc._

/**
 * Auth
 */
object Authentication extends Controller {

  val loginForm = Form(
    tuple(
      "username" -> text,
      "password" -> text
    )
  )

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def logout = Action {
    Redirect(routes.Authentication.login).withNewSession.flashing(
      "success" -> "Successfully logged out."
    )
  }

  def authenticate = Action { implicit request =>

    Redirect(routes.Application.index()).withSession(
      "username" -> loginForm.bindFromRequest().get._1
    )
  }
}
