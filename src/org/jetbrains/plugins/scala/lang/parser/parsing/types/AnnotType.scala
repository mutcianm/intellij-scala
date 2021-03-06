package org.jetbrains.plugins.scala
package lang
package parser
package parsing
package types

import org.jetbrains.plugins.scala.lang.parser.ScalaElementTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.expressions._
import builder.ScalaPsiBuilder

/** 
* @author Alexander Podkhalyuzin
* Date: 06.02.2008
*/

/*
 * AnnotType ::= {Annotation} SimpleType
 */

object AnnotType {
  def parse(builder: ScalaPsiBuilder): Boolean = {
    val annotMarker = builder.mark
    var isAnnotation = false
    //parse Simple type
    if (SimpleType parse builder) {
      val annotationsMarker = builder.mark
      while (!builder.newlineBeforeCurrentToken && Annotation.parse(builder, false)) {isAnnotation = true}

      if (isAnnotation) annotationsMarker.done(ScalaElementTypes.ANNOTATIONS) else annotationsMarker.drop()
      if (isAnnotation) annotMarker.done(ScalaElementTypes.ANNOT_TYPE) else annotMarker.drop()
      true
    }
    else {
      annotMarker.rollbackTo()
      false
    }
  }
}