package services

import play.api.libs.oauth.{OAuthCalculator, RequestToken, ConsumerKey}
import controllers.EventSource

import play.api.libs.ws.WS
import play.api.libs.iteratee.Iteratee
import play.api.mvc.Codec
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Twitter(key: ConsumerKey, token: RequestToken) {

  def publicStream(track: String, eventSource: EventSource): Unit = {
    import java.net.URLEncoder.encode
    WS
      .url(s"${Twitter.BaseUrl}/statuses/filter.json?track=${encode(track, Codec.utf_8.charset)}")
      .sign(OAuthCalculator(key, token))
      .postAndRetrieveStream(Map.empty[String, Seq[String]]) { headers =>
        Iteratee.foreach[Array[Byte]] { bytes =>
          val data = Codec.utf_8.decode(bytes)
          eventSource.sendData(data)
        }
      }
  }

}

object Twitter {

  val BaseUrl = "https://stream.twitter.com/1.1"

  val taa = new Twitter(
    ConsumerKey("TjD297pjoriX7NEMPS6iEQ", "J33Y6rM5Iff14mae6FYxd17IH5EtmOm4m0fqZmsGbI"),
    RequestToken("171419436-n2PSgcbYkCR33asx3R6gBNQMNEKHi4IHY5pZkTsP", "knsppoVWu2UmKtLQBwANlBGXSrAKvDSgnfjOqDGX0")
  )

}