name := "tp-play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaCore
)

resolvers += "Local M2" at Path.userHome.asURL + ".m2/repository"

play.Project.playJavaSettings