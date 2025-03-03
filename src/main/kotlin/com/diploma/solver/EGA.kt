package com.diploma.solver

import com.diploma.data.Individual
import com.diploma.data.Task
import com.diploma.ega.cyclicCrossover
import com.diploma.ega.generateInitialPopulation
import com.diploma.ega.pointMutation
import com.diploma.executor.ExecutorWrapper
import com.diploma.shared.comparator
import com.diploma.util.MAX_GENERATION_NUMBER
import com.diploma.util.MUTATION_PROBABILITY
import com.diploma.util.POPULATION_SIZE
import com.diploma.util.random
import kotlin.random.Random

/**
 * Метод решения задачи теории расписаний при помощи
 * эволюционно-генетического алгоритма (ЭГА)
 *
 * [selectionOperator] - функция с алгоритмом отбора
 *
 * [initialPopulation] - начальная популяция, которую
 * можно использовать вместо случайной генерации
 */
class EGA(
	private val selectionOperator: (List<Individual>, Int) -> List<Individual>,
	private val initialPopulation: MutableList<Individual>? = null
) : Solver {
	override fun solve(tasks: List<Task>): Int {
		val executorWrapper = ExecutorWrapper()
		val population = initialPopulation ?: generateInitialPopulation(tasks).toMutableList()
		var generationsWithoutImprovement = 0
		var bestIndividual = population.minBy { it.fitness }

		while (true) {
			if (generationsWithoutImprovement == MAX_GENERATION_NUMBER)
				break

			val reproductiveSet = mutableListOf<Individual>()
			population.sortWith(comparator)

			for (i in 0..<(POPULATION_SIZE / 2)) {
				// Выбор родителей
				val parentOne = population[i]
				val parentTwo = population[POPULATION_SIZE - i - 1]

				// Кроссовер
				val permutationForChild = cyclicCrossover(parentOne.individual, parentTwo.individual)
				val child = Individual(
					permutationForChild,
					executorWrapper.executeByPermutationWithFines(tasks, permutationForChild)
				)
				reproductiveSet.addLast(child)

				// Мутация
				if (Random.nextFloat() < MUTATION_PROBABILITY) {
					val mutatedPermutation = pointMutation(permutationForChild)
					reproductiveSet.addLast(
						Individual(
							mutatedPermutation,
							executorWrapper.executeByPermutationWithFines(tasks, mutatedPermutation)
						)
					)
				}
			}

			// Выборка g особей на замену из популяции
			var generationCoefficient = 0f
			while (generationCoefficient == 0f)
				generationCoefficient = Random.nextFloat()
			val g = (generationCoefficient * POPULATION_SIZE).toInt()

			reproductiveSet.sortWith(comparator)

			val individualsForRemove = population.random(g)
			val individualsForReplace = selectionOperator(reproductiveSet, g)
			individualsForRemove.forEach {
				population.remove(it)
			}
			population.addAll(individualsForReplace)

			// Проверка на остановку
			val candidate = population.minBy { it.fitness }
			if (bestIndividual.fitness <= candidate.fitness) {
				generationsWithoutImprovement++
			} else {
				bestIndividual = candidate
				generationsWithoutImprovement = 0
			}
		}

		println(
			"Решение при помощи ЭГА с оператором селекции бета-турнир${
				if (selectionOperator.javaClass.name == "com.diploma.MainKt\$main\$solverList$2")
					" и генерацией начальной популяции при помощи эвристики"
				else
					""
			}"
		)
		println(bestIndividual)

		return bestIndividual.fitness
	}
}
