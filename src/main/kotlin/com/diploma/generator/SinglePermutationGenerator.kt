package com.diploma.generator

import com.diploma.data.Permutation
import com.diploma.util.TaskNumber.TASK_NUMBER

/**
 * Singleton для генерации и хранения начальной перестановки
 * для алгоритма hill-climbing. Перестановка не изменяется за
 * всё время работы программы
 */
object SinglePermutationGenerator {
	val singlePermutation: Permutation = generatePermutation()

	private fun generatePermutation(): Permutation =
		Permutation(
			(0..<TASK_NUMBER).shuffled()
		)
}
