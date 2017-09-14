package io.zachgray.kalk

import io.zachgray.kalk.math.ext.toRPNExpression
import io.zachgray.cmdloop.commandLoop

fun main(args:Array<String>) {
    if(!args.isEmpty()) return println(args.joinToString(" ").toRPNExpression().evaluate())

    commandLoop {
        welcomeMessage { "Hi! Enter a mathematical expression to be evaluated, or enter a command." }
        commandPrefix { "/" }
        command("random") { params, loop -> Commands.random(params, loop) }
        default { Commands::evaluate }
        catch { Commands::handleError }
    }
}