package net.kkung.sass

import scopt.immutable.OptionParser
import java.io.File

object SassCli { 

  case class Config(file:Option[String] = None, stdin:Boolean = false)
  
  private val argParser = new OptionParser[Config]("sass-scala", "0.1") { 
    def options = Seq(
      opt("f", "file", "sass file to convert('-' stdin)") { (v:String, c:Config) => { 
        v match { 
          case "-" => c.copy(stdin = true)
          case _ => { 
            val file = new File(v)
            if (!file.exists)
              throw new Throwable("Input file %s does not exist.".format(v))
            c.copy(file = Some(v))
          }
        }
      } })
  }
 
  def main(args: Array[String]) = {

    if (args.length == 0 ) { 
      print(argParser.usage)
      System.exit(1)
    }
   
    argParser.parse(args, Config()) map { 
      config => { 
        config match { 
          case Config(_, true) => { 
            val result = Sass.compile(scala.io.Source.stdin.mkString)
            result match { 
              case (true, str) => print(str); System.exit(0)
              case (false, err) => Console.err.print(err); System.exit(1)
            }
          }
          case Config(Some(file), false) => { 
            val result = Sass.compile(file)
            result match { 
              case (true, str) => print(str); System.exit(0)
              case (false, err) => Console.err.print(err); System.exit(1)
            }
          }
          case Config(None, false) => { 
            print(argParser.usage)
            System.exit(1)
          }
        }
      }
    } getOrElse { 
      
    }

  }
}
