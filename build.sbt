name := "tp-play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaWs,
  "io.reactivex" % "rxjava" % "1.0.0-rc.9",
  "org.zeromq" % "jeromq" % "0.3.4"
)

enablePlugins(PlayJava)
