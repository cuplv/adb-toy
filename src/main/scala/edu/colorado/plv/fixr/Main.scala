package edu.colorado.plv.fixr

import scala.io.{BufferedSource, Codec, Source}
import scala.util.{Try,Success,Failure}

import edu.colorado.plv.fixr.bash.{Cmd, Fail, Lift, Succ}
import edu.colorado.plv.fixr.bash.android.{Aapt, Adb, AmInstrument, Emulator}
import edu.colorado.plv.fixr.bash.utils.{FailTry, SuccTry, doTry, repeat}

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object Utils {
  def readFileUtf8(path: String): Try[String] = Try {
    val source: BufferedSource = Source.fromFile(path)(Codec.UTF8)
    val content = source.mkString
    source.close()
    content
  }
}

object Main extends App {

 if (args.length != 3) {
   println("cmd <adb-server-address> <adb-server-port> <emulator-id>")
   sys.exit(1)
 }
 else  {
   val serverAddress = args(0)
   val portNumber = args(1).toInt
   val emuID = args(2)

   implicit val bashLogger:Logger =
     Logger(LoggerFactory.getLogger("test logger"))

   val instTest = for {
     instTest <- Adb.extend(s"-H $serverAddress -P $portNumber").target(emuID).shell("ls") !
   } yield instTest
   instTest match {
     case SuccTry(_) => println("Success")
     case FailTry(_) => println("Error")
   }
   1
 }
}
