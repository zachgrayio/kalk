package io.zachgray.kalk

import io.zachgray.kalk.math.ext.toRPNExpression
import io.zachgray.cmdloop.commandLoop
import java.util.*

fun main(args:Array<String>) {
    if(!args.isEmpty()) return println(args.joinToString(" ").toRPNExpression().evaluate())
    commandLoop {
        var lastResult:Double? = null
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
                    val expression = it.toRPNExpression()
                    val result = expression.evaluate(lastResult)
                    lastResult = result
                    println("  $result")
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