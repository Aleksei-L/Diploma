package com.diploma.generator

import com.diploma.data.Permutation

/**
 * Функция генерации соседей для перестановки
 *
 * Генерирует ровно `n` различных соседей для перестановки
 */
fun generateNeighbors(permutation: Permutation): Set<Permutation> {
	val neighbors = mutableSetOf<Permutation>()

	while (neighbors.size < permutation.numbersList.size) {
		val firstIndex = (0..<permutation.numbersList.size).random()
		var secondIndex = -1
		do {
			val random = (0..<permutation.numbersList.size).random()
			if (random != firstIndex)
				secondIndex = random
		} while (secondIndex == -1)

		val newPermutation = permutation.numbersList.toMutableList()
		newPermutation[firstIndex] =
			newPermutation[secondIndex].also { newPermutation[secondIndex] = newPermutation[firstIndex] }

		neighbors.add(Permutation(newPermutation))
	}

	return neighbors
}
