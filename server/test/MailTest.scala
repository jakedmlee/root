import play.api.libs.mailer._
import org.apache.commons.mail.EmailAttachment
import javax.inject.Inject

class MailTest @Inject() (mailerClient: MailerClient) {

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

object MailTest {
  def main(str: Array[String]) = {
    val mailerClient = new MailerClient {
      override def send(data: Email): String = ???
    }
    val mailTest = new MailTest
    mailTest.sendEmail
  }
}