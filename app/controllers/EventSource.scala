package controllers

import play.mvc.Results.Chunks
import play.mvc.Results.Chunks.Out

abstract class EventSource extends Chunks[String](play.core.j.JavaResults.writeString("text/event-stream")(play.api.mvc.Codec.javaSupported("utf-8"))) {

  private var out: Out[String] = _

  def onReady(out: Out[String]): Unit = {
    this.out = out
    onConnected()
  }

  def onConnected(): Unit

  def sendData(data: String): Unit = out.write(
    (for (line <- data.split("\n")) yield {
      s"data:$line\n"
    }).mkString ++ "\n\n"
  )

}
