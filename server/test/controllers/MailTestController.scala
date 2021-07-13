package controllers

import com.mohiva.play.silhouette.api.actions.UserAwareActionBuilder
import play.api.libs.mailer._

import java.io.File
import org.apache.commons.mail.EmailAttachment
import play.api.i18n.I18nSupport
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import scala.concurrent.Future

import javax.inject.{Inject, Singleton}

class MailerService (mailerClient: MailerClient) {

  def sendEmail = {
    val cid = "1234"
    val email = Email(
      "another Simple email",
      "Lee Daemyeong FROM <jakedmlee@buster.codesnippet.kr>",
      Seq("Jakedmlee TO <jakedmlee@gmail.com>"),
      bodyText = Some("A text message")
      //bodyHtml = Some(s"""<html><body><p>An <b>html</b> message with cid <img src="cid:$cid"></p></body></html>""")
    )
    mailerClient.send(email)
  }
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class MailTestController @Inject() (
  scc: SilhouetteControllerComponents,
  mailerClient: MailerClient,
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
    val a = new MailerService(mailerClient)
    a.sendEmail
    Future.successful(Ok(home(request.identity)))
  }
}