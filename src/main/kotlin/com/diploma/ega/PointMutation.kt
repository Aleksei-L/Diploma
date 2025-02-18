package com.diploma.ega

import com.diploma.data.Permutation

/**
 * TODO
 * функция гарантированно вызовет мутацию у хромосомы
 * ВЕРОЯТНОСТЬ МУТАЦИИ ДОЛЖНА БЫТЬ ВЫНЕСЕНА В ОБЩИЙ ПОТОК АЛГОРИТМА
 */
fun pointMutation(chromosome: Permutation): Permutation {
	val first = (0..<chromosome.numbersList.size).random()
	val second = (first + 1) % chromosome.numbersList.size

	val mutatedChromosome = chromosome.numbersList.toMutableList()
	val temp = mutatedChromosome[first]
	mutatedChromosome[first] = mutatedChromosome[second]
	mutatedChromosome[second] = temp

	return Permutation(mutatedChromosome)
}
