package io.zachgray.kalk.math.ext

import io.zachgray.kalk.math.Operator

operator fun Operator.compareTo(other: Operator):Int {
    return when {
        this.precedence > other.precedence -> 1
        this.precedence == other.precedence -> 0
        else -> -1
    }
}