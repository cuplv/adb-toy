enablePlugins(JavaAppPackaging)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "edu.colorado.plv.fixr",
      scalaVersion := "2.12.6",
      version      := "0.0.1"
    )),
    name := "adb_toy",
    mainClass in (Compile, run) := Some("edu.colorado.plv.fixr.Main"),
    libraryDependencies += "edu.colorado.plv.fixr" %% "scalabashing" % "1.0-SNAPSHOT"
  )


