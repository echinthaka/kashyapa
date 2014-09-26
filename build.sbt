name := "kashyapa"

version := "1.0"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "typesafe-repository" at "http://repo.typesafe.com/typesafe/releases/",
  "clojars-repository" at "https://clojars.org/repo"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  // ==================================================
  // Protobuf / Kryo
  // ==================================================
  "net.sandrogrzicic" %% "scalabuff-runtime" % "1.3.7",
  "com.twitter" % "bijection-core_2.10" % "0.6.3",
  "com.twitter" % "bijection-protobuf_2.10" % "0.6.3",
    // ==================================================
  // Test Dependencies
  // ==================================================
  "org.specs2" %% "specs2" % "2.4.2" % "test"
)

// Required IntelliJ workaround.  This tells `sbt gen-idea` to include scala-reflect as a compile dependency (and not
// merely as a test dependency), which we need for TypeTag usage.
libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)
