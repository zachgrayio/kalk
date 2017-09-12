package io.zachgray.kalk.math

/**
 * Supported operators. To add one:
 * 1: add the token to the `tokens` val on the companion object.
 * 2: add an entry to the `fromString` when expression so that operator string token can be mapped to the Operator
 * 3: add an Operator entry with operator metadata
 * 4: expand `operate` function with operator impl
 */
sealed class Operator(
    val stringRepresentation:String,
    val precedence:Int,
    val isRightAssociative:Boolean
) {
    companion object {
        // 1
        val tokens = "><=\\^%*+\\-"

        // 2
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

    // 3
    object eq :     Operator(stringRepresentation = "=",  precedence = 0, isRightAssociative = false)
    object leq :    Operator(stringRepresentation = "<=", precedence = 0, isRightAssociative = false)
    object geq :    Operator(stringRepresentation = ">=", precedence = 0, isRightAssociative = false)
    object lt :     Operator(stringRepresentation = "<",  precedence = 0, isRightAssociative = false)
    object gt :     Operator(stringRepresentation = ">",  precedence = 0, isRightAssociative = false)
    object plus :   Operator(stringRepresentation = "+",  precedence = 1, isRightAssociative = false)
    object minus :  Operator(stringRepresentation = "-",  precedence = 1, isRightAssociative = false)
    object times :  Operator(stringRepresentation = "*",  precedence = 2, isRightAssociative = false)
    object div :    Operator(stringRepresentation = "/",  precedence = 2, isRightAssociative = false)
    object mod :    Operator(stringRepresentation = "%",  precedence = 2, isRightAssociative = false)
    object pow :    Operator(stringRepresentation = "^",  precedence = 3, isRightAssociative = true)

    // 4
    fun operate(right:Double, left:Double):Double = when(this) {
        is eq ->    if(left == right)   1.0 else 0.0
        is leq ->   if (left <= right)  1.0 else 0.0
        is geq ->   if(left >= right)   1.0 else 0.0
        is lt ->    if(left < right)    1.0 else 0.0
        is gt ->    if(left > right)    1.0 else 0.0
        is plus ->  left.plus(right)
        is minus -> left.minus(right)
        is times -> left.times(right)
        is div ->   left.div(right)
        is mod ->   left.rem(right)
        is pow ->   Math.pow(left, right)
    }
}