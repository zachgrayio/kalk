package io.zachgray.kalk.math

/**
 * Supported operators. To add one:
 * 1: add an Operator entry which implements `operate()`
 * 2: add the token to the `tokens` val on the companion object.
 */
sealed class Operator(val stringRepresentation:String, val precedence:Int, val isRightAssociative:Boolean) {
    abstract fun operate(right:Double, left:Double):Double

    // 1
    object plus : Operator(stringRepresentation = "+", precedence = 0, isRightAssociative = false) {
        override fun operate(right: Double, left: Double) = left.plus(right)
    }

    object minus : Operator(stringRepresentation = "-", precedence = 0, isRightAssociative = false) {
        override fun operate(right: Double, left: Double) = left.minus(right)
    }

    object times : Operator(stringRepresentation = "*", precedence = 1, isRightAssociative = false) {
        override fun operate(right: Double, left: Double) = left.times(right)
    }

    object div : Operator(stringRepresentation = "/", precedence = 1, isRightAssociative = false) {
        override fun operate(right: Double, left: Double) = left.div(right)
    }

    object mod : Operator(stringRepresentation = "%", precedence = 1, isRightAssociative = false) {
        override fun operate(right: Double, left: Double) = left.rem(right)
    }

    object pow : Operator(stringRepresentation = "^", precedence = 2, isRightAssociative = true) {
        override fun operate(right: Double, left: Double) = Math.pow(left, right)
    }

    companion object {
        // 2
        val tokens = "\\^%*+\\-"

        fun fromString(representation:String) = when(representation) {
            plus.stringRepresentation -> plus
            minus.stringRepresentation -> minus
            times.stringRepresentation -> times
            div.stringRepresentation -> div
            mod.stringRepresentation -> mod
            pow.stringRepresentation -> pow
            else -> null
        }
    }

    override fun toString(): String {
        return stringRepresentation
    }
}