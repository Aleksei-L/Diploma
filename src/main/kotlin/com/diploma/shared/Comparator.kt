package com.diploma.shared

import com.diploma.data.Individual

/**
 * TODO
 */
val comparator = Comparator<Individual> { a, b ->
	when {
		a.fitness < b.fitness -> -1
		a.fitness == b.fitness -> 0
		else -> 1
	}
}
