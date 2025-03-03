package com.diploma.ega

import com.diploma.data.Individual

/**
 * Оператор отбора для ЭГА
 *
 * Использует принцип линейной ранговой селекции
 *
 * Список [population] требуется передавать отсортированным
 */
fun linearRankSelection(population: List<Individual>, individualsCount: Int): List<Individual> {
	val anonymizedList = mutableListOf<Individual>()
	var rank = 1

	for (i in population.indices) {
		anonymizedList.addLast(
			Individual(
				population[i].individual,
				rank++
			)
		)
	}

	val winners = mutableListOf<Individual>()
	for (i in 0..<individualsCount) {
		val position = roulette(anonymizedList)
		winners.addLast(population[position])
	}

	return winners
}
