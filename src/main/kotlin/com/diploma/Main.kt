package com.diploma

import com.diploma.generators.generatePermutations

fun main() {
	val n = 3
	val permutations = generatePermutations(n)
	for (perm in permutations) {
		println(perm)
	}
}
