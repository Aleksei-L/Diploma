package com.diploma.shared

import com.diploma.data.Individual

/**
 * Компаратор для сравнения двух особей для ЭГА
 *
 * При использовании для сортировки отсортирует особи
 * в возрастающем порядке
 */
val comparator = Comparator<Individual> { a, b ->
	when {
		a.fitness < b.fitness -> -1
		a.fitness == b.fitness -> 0
		else -> 1
	}
}
