name := "Libsass for Scala"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
  "net.java.dev.jna" % "jna" % "3.4.0",
  "com.github.scopt" %% "scopt" % "2.1.0",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)

resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"