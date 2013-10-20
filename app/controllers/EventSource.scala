package controllers

import play.mvc.Results.Chunks
import play.mvc.Results.Chunks.Out

/**
 * Permet de « streamer » un un résultat au format EventSource
 * cf specification: http://dev.w3.org/html5/eventsource/
 */
abstract class EventSource extends Chunks[String](play.core.j.JavaResults.writeString("text/event-stream")(play.api.mvc.Codec.javaSupported("utf-8"))) {

  private var out: Out[String] = _

  def onReady(out: Out[String]): Unit = {
    this.out = out
    onConnected()
  }

  /**
   * Méthode appelée lorsque la connexion est établie avec le client.
   */
  def onConnected(): Unit

  /**
   * Envoie un message de données
   * @param data Contenu du message
   */
  def sendMessage(data: String): Unit = out.write(
    (for (line <- data.split("\n")) yield {
      s"data:$line\n"
    }).mkString ++ "\n\n"
  )

}
