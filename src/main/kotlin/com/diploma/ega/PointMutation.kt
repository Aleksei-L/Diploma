package com.diploma.ega

import com.diploma.data.Permutation

/**
 * Оператор мутации для ЭГА
 *
 * Представляет собой генную мутацию
 *
 * **Функция гарантированно вызовет мутацию у хромосомы**, вероятность
 * мутации должна быть вынесена в основной алгоритм
 *
 * [chromosome] - перестановка, для которой будет выполнена мутация
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
