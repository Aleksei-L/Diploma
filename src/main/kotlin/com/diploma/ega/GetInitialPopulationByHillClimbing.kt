package com.diploma.ega

import com.diploma.data.Individual
import com.diploma.executor.ExecutorWrapper
import com.diploma.generator.SinglePermutationGenerator
import com.diploma.generator.generateNeighbors
import com.diploma.shared.Tasks.tasks
import com.diploma.util.POPULATION_SIZE

/**
 * Оператор генерации начальной популяции для ЭГА на основе
 * алгоритма [hill-climbing][com.diploma.solver.HillClimbing]
 */
fun getInitialPopulationByHillClimbing(
	executorWrapper: ExecutorWrapper
): MutableList<Individual> {
	val population = mutableListOf<Individual>()

	for (i in 0..<POPULATION_SIZE) {
		var permutation = SinglePermutationGenerator.singlePermutation
		var neighbors = generateNeighbors(permutation)
		var minim = Int.MAX_VALUE

		while (true) {
			var fineHasChanged = false

			for (j in neighbors) {
				val fine = executorWrapper.executeByPermutationWithFines(tasks, j)
				if (fine < minim) {
					minim = fine
					permutation = j
					fineHasChanged = true
				}
			}

			if (!fineHasChanged) {
				population.addLast(
					Individual(permutation, minim)
				)
				break
			}

			neighbors = generateNeighbors(permutation)
		}
	}

	return population
}
