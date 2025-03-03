package com.diploma.ega

import com.diploma.data.Individual
import com.diploma.data.Permutation
import com.diploma.data.Task
import com.diploma.executor.ExecutorWrapper
import com.diploma.util.POPULATION_SIZE
import com.diploma.util.TASK_NUMBER

/**
 * Оператор генерации начальной популяции для ЭГА
 *
 * Использует принцип отрицательного ассоциативного скрещивания
 */
fun generateInitialPopulation(
	tasks:List<Task>
): List<Individual> {
	val executorWrapper = ExecutorWrapper()
	val population = mutableListOf<Individual>()

	for (i in 0..<POPULATION_SIZE) {
		val permutation = Permutation((0..<TASK_NUMBER).shuffled())
		val fine = executorWrapper.executeByPermutationWithFines(tasks, permutation)
		population.addLast(Individual(permutation, fine))
	}

	return population
}
