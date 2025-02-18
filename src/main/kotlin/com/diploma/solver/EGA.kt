package com.diploma.solver

import com.diploma.data.Individual
import com.diploma.data.Task
import com.diploma.ega.betaTournament
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
 * TODO
 */
class EGA : Solver {
	override fun solve(tasks: List<Task>): Int {
		val executorWrapper = ExecutorWrapper()
		val population = generateInitialPopulation().toMutableList()
		var generationsWithoutImprovement = 0
		var bestIndividual = population.minBy { it.fitness }
		var generationNumber = 1
		var totalGenerations = 1 //TODO remove

		while (true) {
			if (generationsWithoutImprovement == MAX_GENERATION_NUMBER || totalGenerations == 50)
				break

			val reproductiveSet = mutableListOf<Individual>()
			population.sortWith(comparator)

			println("Поколение №$generationNumber")
			population.forEach {
				println(it)
			}//TODO
			println("Лучшая особь - $bestIndividual")
			println()

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

			val individualsForRemove = population.random(g)
			val individualsForReplace = betaTournament(reproductiveSet, g)
			individualsForRemove.forEach {
				population.remove(it)
			}
			population.addAll(individualsForReplace)

			// Проверка на остановку ЭГА - 10 поколений без улучшения решения
			val candidate = population.minBy { it.fitness }
			if (/*bestIndividual == candidate*/bestIndividual.fitness <= candidate.fitness) {
				generationsWithoutImprovement++
			} else {
				bestIndividual = candidate
				generationsWithoutImprovement = 0
			}
			generationNumber++
			totalGenerations++
		}

		return bestIndividual.fitness
	}
}
