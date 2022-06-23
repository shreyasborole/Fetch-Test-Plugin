ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
      name := "FetchTestPlugin",
      idePackagePrefix := Some("org.fetch.plugin"),
      libraryDependencies += "com.themillhousegroup" %% "scoup" % "1.0.0",
      libraryDependencies += "com.lihaoyi" %% "fansi" % "0.3.0"
  )
