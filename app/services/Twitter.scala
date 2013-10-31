package services

import play.api.libs.oauth.{OAuthCalculator, RequestToken, ConsumerKey}
import controllers.EventSource

import play.api.libs.ws.WS
import play.api.libs.iteratee.Iteratee
import play.api.mvc.Codec
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Bibliothèque utilitaire pour effectuer des requêtes HTTP sur l’API streaming de Twitter.
 * cf. https://dev.twitter.com/
 * @param key OAuth consumer key
 * @param token OAuth request token
 */
class Twitter(key: ConsumerKey, token: RequestToken) {

  /**
   * Appelle le service `statuses/filter`
   * cf. https://dev.twitter.com/docs/api/1.1/post/statuses/filter
   * @param track Mot-clé à rechercher
   * @param eventSource Flux vers lequel les tweets seront transmis
   */
  def publicStream(track: String, eventSource: EventSource): Unit = {
    WS
      .url(s"${Twitter.BaseUrl}/statuses/filter.json")
      .withQueryString("track" -> track)
      .sign(OAuthCalculator(key, token))
      .postAndRetrieveStream(Map.empty[String, Seq[String]]) { headers =>
        Iteratee.foreach[Array[Byte]] { bytes =>
          val data = Codec.utf_8.decode(bytes)
          eventSource.sendMessage(data)
        }
      }
  }

}

object Twitter {

  val BaseUrl = "https://stream.twitter.com/1.1"

  // Service Twitter sous l’identité de l’application TP-TAA
  val taa = new Twitter(
    ConsumerKey("TjD297pjoriX7NEMPS6iEQ", "J33Y6rM5Iff14mae6FYxd17IH5EtmOm4m0fqZmsGbI"),
    RequestToken("171419436-n2PSgcbYkCR33asx3R6gBNQMNEKHi4IHY5pZkTsP", "knsppoVWu2UmKtLQBwANlBGXSrAKvDSgnfjOqDGX0")
  )

}