package io.zachgray.kalk

import io.zachgray.kalk.math.ext.toRPNExpression
import io.zachgray.cmdloop.commandLoop
import java.util.*
import kotlin.collections.HashMap

fun main(args:Array<String>) {
    if(!args.isEmpty()) return println(args.joinToString(" ").toRPNExpression().evaluate())
    commandLoop {
        // state
        var lastResult:Double? = null
        val variables = HashMap<String, Double>()

        welcomeMessage {
            "Hi! Enter a mathematical expression to be evaluated, or enter a command."
        }
        commandPrefix {
            "/"
        }
        command("random") { params, _ ->
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
                lastResult = when {
                    min == 0 && max == 1 -> Math.random()
                    else -> Random().nextInt(max + 1 - min).plus(min).toDouble()
                }
                println("  $lastResult")
            } catch(t:Throwable) {
                println("  invalid parameters. usage: /random 1 10")
            }
        }
        default {
            { input ->
                input?.let {
                    val parts = it.split("\\s=|=".toRegex())
                    val variable = parts.firstOrNull()
                    // if variables are defined, inject their values and evaluate expression, otherwise just evaluate
                    fun evaluate(expressionString:String):Double {
                        var injectedExpressionString:String? = null
                        variables.keys.forEach { k ->
                            injectedExpressionString = expressionString.replace("$k\\s|\\s$k\\s|$k".toRegex(), " ${variables[k]} ")//todo: better matching
                        }
                        return (injectedExpressionString ?: expressionString).toRPNExpression().evaluate(seed = lastResult)

                    }
                    // assignment
                    if(parts.size > 1 && variable != null) {
                        val result = evaluate(parts.last())
                        variables[variable] = result
                        println("  $variable = $result")
                    }
                    // not assignment
                    else {
                        val result = evaluate(it)
                        lastResult = result
                        println("  $result")
                    }
                }
            }
        }
        catch {
            { _ ->
                println("  invalid expression, please try again.")
            }
        }
    }
}