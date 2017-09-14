package io.zachgray.kalk

/**
 * Global application state
 */
object State {
    var results: MutableList<Double> = mutableListOf()
    val variables = HashMap<String, Double>()
}