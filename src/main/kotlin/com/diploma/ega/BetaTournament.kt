package com.diploma.ega

import com.diploma.data.Individual
import com.diploma.executor.ExecutorWrapper
import com.diploma.shared.Tasks.tasks

/**
 * TODO
 */
fun betaTournament(population: List<Individual>, individualsCount: Int): List<Individual> {
	val executorWrapper = ExecutorWrapper()
	val beta = (2..<population.size / 2).random()
	val winners = mutableListOf<Individual>()

	for (i in 0..<individualsCount) {
		val tour = mutableListOf<Individual>()
		for (j in 0..<beta)
			tour.addLast(population.random())
		val permutationsList = tour.map { it.individual }
		val executionResult = executorWrapper.executeByAllPermutationsWithFines(tasks, permutationsList)
		winners.addLast(
			Individual(
				executionResult.first,
				executionResult.second
			)
		)
	}

	return winners
}
