package net.kkung.sass

import com.sun.jna.Memory
import com.sun.jna.Pointer
import net.kkung.libsass.sass_options
import net.kkung.libsass.LibsassLibrary


/**
 * Sass object
 *
 * ==Overview==
 *
 * {{{
 * scala> import net.kkung.sass.Sass
 * scala> Sass.compile("p { width: 1+1px; }")
 * (true, "p { width: 2px; }")
 * }}}
 */ 
object Sass { 

  val libSass:LibsassLibrary = LibsassLibrary.INSTANCE

  implicit private def stringToPointer(str:String):Pointer = { 
    val memory = new Memory(str.length + 1)
    memory.setString(0, str)
    memory
  }

  /** 
  * compile scss to css from source string
  *
  * @param source scss string
  * @param options compile option
  */ 
  def compile(source:String, options:sass_options = new sass_options):Tuple2[Boolean, String] = { 
    val context = libSass.sass_new_context
    context.source_string = source
    context.options = options

    val result = libSass.sass_compile(context)
    context.error_status match { 
      case 0 => Tuple2(true, context.output_string.getString(0))
      case _ => Tuple2(false, context.error_message.getString(0))
    }
  }

  /**
   * compile scss to css from file(path)
   *
   * @param file scss file path
   * @param options compile option
   */
  def compileFile(file:String, options:sass_options = new sass_options):Tuple2[Boolean, String] = { 

    val context = libSass.sass_new_file_context
    context.input_path = file
    context.options = options

    val result = libSass.sass_compile_file(context)
    context.error_status match { 
      case 0 => Tuple2(true, context.output_string.getString(0))
      case _ => Tuple2(false, context.error_message.getString(0))
    }
  
  }
}
