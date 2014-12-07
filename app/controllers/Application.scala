package controllers

import play.api.mvc._

object Application extends Controller {

  def index = Action { implicit request =>
    request.session.get("username").map { user =>
      Ok(views.html.index(user))
    }.getOrElse {
      Redirect(routes.Authentication.login())
    }
  }
}