package controllers

import com.mohiva.play.silhouette.api.LogoutEvent
import com.mohiva.play.silhouette.api.actions.UserAwareRequest

import javax.inject._
import play.api._
import play.api.i18n._
import play.api.mvc._
import utils.route.Calls

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ApplicationController @Inject() (
  scc: SilhouetteControllerComponents,
  home: views.html.home)
  extends SilhouetteController(scc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = UserAwareAction.async { implicit request =>
    Future.successful(Ok(home(request.identity)))
  }

  def signOut = SecuredAction.async { implicit request =>
    val result = Redirect(Calls.home)
    eventBus.publish(LogoutEvent(request.identity, request))
    authenticatorService.discard(request.authenticator, result)
  }
}