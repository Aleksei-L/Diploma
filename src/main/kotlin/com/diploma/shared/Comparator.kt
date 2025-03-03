package com.diploma.shared

import com.diploma.data.Individual

/**
 * Компаратор для сравнения двух особей для ЭГА
 *
 * При использовании для сортировки отсортирует особи
 * в убывающем порядке
 */
val comparator = Comparator<Individual> { a, b ->
	when {
		b.fitness < a.fitness -> -1
		b.fitness == a.fitness -> 0
		else -> 1
	}
}
