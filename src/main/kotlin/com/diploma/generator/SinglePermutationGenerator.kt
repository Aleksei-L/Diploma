package com.diploma.generator

import com.diploma.data.Permutation

class SinglePermutationGenerator {
	private var singlePermutation: Permutation? = null

	/**
	 * Функция генерации начальной перестановки для алгоритма
	 * hill-climbing. Перестановка не изменяется за время
	 * существования конкретного экземпляра класса
	 */
	fun generatePermutation(taskNumber: Int): Permutation {
		if (singlePermutation != null)
			return singlePermutation!!

		singlePermutation = Permutation(
			(0..<taskNumber).shuffled()
		)

		return singlePermutation!!
	}

}
