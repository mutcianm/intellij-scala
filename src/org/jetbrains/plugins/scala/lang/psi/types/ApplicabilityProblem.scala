package org.jetbrains.plugins.scala.lang.psi.types

import nonvalue.Parameter
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunction
import org.jetbrains.plugins.scala.lang.psi.api.statements.params.{ScParameters, ScParameterClause, ScParameter}
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression

/**
 * Pavel.Fatin, 02.06.2010
 */

sealed case class ApplicabilityProblem(description: String = "unknown") 

// function definition problems
case class MultipleDefinitions extends ApplicabilityProblem
case class MultipleDefinitionVariants extends ApplicabilityProblem
case class MalformedDefinition extends ApplicabilityProblem

// call syntax problems
case class PositionAlfterNamedArguemnt extends ApplicabilityProblem
case class ParameterSpecifiedMultipleTimes extends ApplicabilityProblem

// call applicability problem
case class DoesNotTakeParameters extends ApplicabilityProblem
case class ExcessArgument(argument: ScExpression) extends ApplicabilityProblem
case class MissedParametersClause(clause: ScParameterClause) extends ApplicabilityProblem
case class MissedParameter(parameter: Parameter) extends ApplicabilityProblem
case class MissedImplicitParameter(parameter: Parameter) extends ApplicabilityProblem
case class TypeMismatch(expression: ScExpression, expectedType: ScType) extends ApplicabilityProblem
