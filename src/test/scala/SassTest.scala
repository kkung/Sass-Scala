package net.kkung.sass.test

import net.kkung.sass.Sass
import org.scalatest.FlatSpec
import java.io.{File, PrintWriter}

class SassTest extends FlatSpec { 

    private val template:String = "p { width: 1 + 1px; }"
    private val expected:String = """p {
  width: 2px; }
"""

  "Sass" should "compile template from string" in { 
    assert(Sass.compile(template) === (true, expected))
  }

  it should "compile template from file" in { 

    val templateFile:File = File.createTempFile("sass-scala", ".scss")
    templateFile.deleteOnExit

    val writer:PrintWriter = new PrintWriter(templateFile)
    writer.print(template)
    writer.close

    assert(Sass.compileFile(templateFile.getAbsolutePath) === (true, expected))
    
  }

}






