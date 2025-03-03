package com.diploma.ega

import com.diploma.data.Individual
import kotlin.random.Random

/**
 * Рулетка для случайного выбора особи из списка [individuals]
 *
 * Список [individuals] требуется передавать отсортированным
 *
 * Возвращает позицию в списке [individuals]
 */
fun roulette(individuals: List<Individual>): Int {
	val fitnessSum = individuals.sumOf { it.fitness }
	val random = Random.nextInt(0, fitnessSum)

	if (random <= individuals[0].fitness)
		return 0

	var partlySum = individuals[0].fitness
	for (i in 1..individuals.size - 2) {
		if (random <= partlySum + individuals[i].fitness)
			return i
		else
			partlySum += individuals[i].fitness
	}

	return individuals.size - 1
}
