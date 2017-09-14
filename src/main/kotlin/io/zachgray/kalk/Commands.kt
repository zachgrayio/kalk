package io.zachgray.kalk

import io.zachgray.cmdloop.CommandLoop
import io.zachgray.cmdloop.LoopControlOperator
import io.zachgray.kalk.math.ext.toRPNExpression
import java.util.*

object Commands {
    /**
     * /random command implementation
     */
    fun random(params:List<String>, loop: CommandLoop): LoopControlOperator? {
        try {
            val intParams = params
                .map { it.toIntOrNull() }
                .filter { it != null }
                .map { it!! }
            val min = when {
                intParams.size == 2 -> intParams.first()
                else -> 1
            }
            val max = when {
                intParams.size == 2 -> intParams.last()
                intParams.size == 1 -> intParams.first()
                else -> 100
            }
            State.results.add(when {
                min == 0 && max == 1 -> Math.random()
                else -> Random().nextInt(max + 1 - min).plus(min).toDouble()
            })
            println("  ${State.results.last()}")
        } catch(t:Throwable) {
            println("  invalid parameters. usage: /random 1 10")
        }
        return null
    }

    /**
     * Evaluates non-command input, evaluating mathematical expressions.
     */
    fun evaluate(input:String?) {
        input?.let {
            val parts = it.split("\\s=|=".toRegex())
            val variable = parts.firstOrNull()
            // if variables are defined, inject their values and evaluate expression, otherwise just evaluate
            fun evaluate(expressionString:String):Double {
                var injectedExpressionString:String? = null
                State.variables.keys.forEach { k ->
                    injectedExpressionString = expressionString.replace("$k\\s|\\s$k\\s|$k".toRegex(), " ${State.variables[k]} ")//todo: better matching
                }
                return (injectedExpressionString ?: expressionString).toRPNExpression().evaluate(seed = State.results.lastOrNull())

            }
            // assignment
            if(parts.size > 1 && variable != null) {
                val result = evaluate(parts.last())
                State.variables[variable] = result
                println("  $variable = $result")
            }
            // not assignment
            else {
                val result = evaluate(it)
                State.results.add(result)
                println("  $result")
            }
        }
    }

    /**
     * Error handler
     */
    fun handleError(t:Throwable) {
        println("  invalid expression, please try again.")
    }
}
