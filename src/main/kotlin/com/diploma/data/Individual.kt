package com.diploma.data

/**
 * TODO
 * Представляет собой особь для ЭГА
 * Включает перестановку и штраф на ней
 */
data class Individual(
	val individual: Permutation,
	val fitness: Int
) {
	override fun toString(): String = "Особь: $individual - $fitness"
}
