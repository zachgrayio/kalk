package io.zachgray.kalk

import example.math.ext.toRPNExpression
import io.zachgray.cmdloop.commandLoop

fun main(args:Array<String>) {
    if(!args.isEmpty()) return println(args.joinToString(" ").toRPNExpression().evaluate())
    commandLoop {
        welcomeMessage {
            "Hi! Enter a mathematical expression to be evaluated, or enter a command."
        }
        commandPrefix {
            "/"
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