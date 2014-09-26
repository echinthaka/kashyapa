resolvers ++= Seq(
  "sbt-plugin-releases-repo" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases",
  "sbt-idea-repository" at "http://mpeltonen.github.io/maven/"
)

resolvers += Resolver.url("bintray-sbt-plugin-releases-masseguillaume",
  url("http://dl.bintray.com/masseguillaume/maven")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.sbt" % "sbt-scalabuff" % "1.3.7")

// https://github.com/mpeltonen/sbt-idea
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

