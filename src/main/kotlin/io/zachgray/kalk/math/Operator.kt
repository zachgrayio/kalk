package io.zachgray.kalk.math

/**
 * Supported operators. To add one:
 * 1: add an Operator entry which implements `operate()`
 * 2: add the token to the `tokens` val on the companion object.
 */
sealed class Operator(
    val stringRepresentation:String,
    val precedence:Int,
    val isRightAssociative:Boolean,
    val operateFunc: (left: Double, right: Double) -> Double
) {
    fun operate(right:Double, left:Double):Double = operateFunc(left, right)

    // 1
    object eq : Operator(
        stringRepresentation = "=",
        precedence = 0,
        isRightAssociative = false,
        operateFunc = { left, right -> if(left == right) 1.0 else 0.0 }
    )

    object leq : Operator(
        stringRepresentation = "<=",
        precedence = 0,
        isRightAssociative = false,
        operateFunc = { left, right -> if (left <= right) 1.0 else 0.0 })

    object geq : Operator(
        stringRepresentation = ">=",
        precedence = 0,
        isRightAssociative = false,
        operateFunc = { left, right -> if(left >= right) 1.0 else 0.0 })

    object lt : Operator(
        stringRepresentation = "<",
        precedence = 0,
        isRightAssociative = false,
        operateFunc = { left, right -> if(left < right) 1.0 else 0.0 })

    object gt : Operator(
        stringRepresentation = ">",
        precedence = 0,
        isRightAssociative = false,
        operateFunc = { left, right -> if(left > right) 1.0 else 0.0 })


    object plus : Operator(
        stringRepresentation = "+",
        precedence = 1,
        isRightAssociative = false,
        operateFunc = { left, right -> left.plus(right) })

    object minus : Operator(
        stringRepresentation = "-",
        precedence = 1,
        isRightAssociative = false,
        operateFunc = { left, right -> left.minus(right) })

    object times : Operator(
        stringRepresentation = "*",
        precedence = 2,
        isRightAssociative = false,
        operateFunc = { left, right -> left.times(right) })

    object div : Operator(
        stringRepresentation = "/",
        precedence = 2,
        isRightAssociative = false,
        operateFunc = { left, right -> left.div(right) })

    object mod : Operator(
        stringRepresentation = "%",
        precedence = 2,
        isRightAssociative = false,
        operateFunc = { left, right -> left.rem(right) })

    object pow : Operator(
        stringRepresentation = "^",
        precedence = 3,
        isRightAssociative = true,
        operateFunc = { left, right -> Math.pow(left, right) }
    )

    companion object {
        // 2
        val tokens = "><=\\^%*+\\-"

        fun fromString(representation:String) = when(representation) {
            eq.stringRepresentation -> eq
            leq.stringRepresentation -> leq
            geq.stringRepresentation -> geq
            lt.stringRepresentation -> lt
            gt.stringRepresentation -> gt
            plus.stringRepresentation -> plus
            minus.stringRepresentation -> minus
            times.stringRepresentation -> times
            div.stringRepresentation -> div
            mod.stringRepresentation -> mod
            pow.stringRepresentation -> pow
            else -> null
        }
    }

    override fun toString(): String = stringRepresentation
}