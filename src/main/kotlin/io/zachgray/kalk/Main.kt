package io.zachgray.kalk

import io.zachgray.kalk.math.ext.toRPNExpression
import io.zachgray.cmdloop.commandLoop
import java.util.*

fun main(args:Array<String>) {
    if(!args.isEmpty()) return println(args.joinToString(" ").toRPNExpression().evaluate())
    commandLoop {
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
                println("  ${Random().nextInt(max + 1 - min).plus(min)}")
            } catch(t:Throwable) {
                println("  invalid parameters. usage: /random 1 10")
            }
        }
        default {
            { input ->
                input?.let {
                    val expression = it.toRPNExpression()
                    println("  ${expression.evaluate()}")
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