package com.diploma.data

/**
 * Класс, представляющий особь для ЭГА
 *
 * [individual] - [перестановка][com.diploma.data.Permutation]
 *
 * [fitness] - штраф достигнутый на данной перестановке.
 * Вычисляется при помощи [исполнителя][com.diploma.executor.ExecutorWrapper]
 */
data class Individual(
	val individual: Permutation,
	val fitness: Int
) {
	override fun toString(): String = "Особь: $individual - $fitness"
}
