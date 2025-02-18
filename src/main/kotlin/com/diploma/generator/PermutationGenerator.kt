package com.diploma.generator

import com.diploma.data.Permutation
import com.diploma.util.TASK_NUMBER

/**
 * Функция генерации всех `n!` перестановок
 *
 * Перестановка будет содержать элементы от `0` до `n-1` включительно,
 * но так как основное её применение - передача на вход исполнителю,
 * где она играет роль индексов в массиве задач, то такое поведение
 * является приемлемым
 */
fun generatePermutations(): List<Permutation> {
	val result = mutableListOf<Permutation>()
	val elements = (0..<TASK_NUMBER).toMutableList()
	val indices = IntArray(TASK_NUMBER) { 0 }

	result.add(Permutation(elements.toList()))

	var i = 0
	while (i < TASK_NUMBER) {
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
