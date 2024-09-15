package com.diploma.generators

import com.diploma.data.Permutation

fun generatePermutations(n: Int): Set<Permutation> {
	val result = mutableSetOf<Permutation>()
	val elements = (0..<n).toMutableList()
	val indices = IntArray(n) { 0 }

	result.add(Permutation(elements.toList()))

	var i = 0
	while (i < n) {
		if (indices[i] < i) {
			if (i % 2 == 0)
				elements[0] = elements[i].also { elements[i] = elements[0] }
			else
				elements[indices[i]] = elements[i].also { elements[i] = elements[indices[i]] }
			result.add(Permutation(elements.toList()))
			indices[i]++
			i = 0
		} else {
			indices[i] = 0
			i++
		}
	}

	return result
}
