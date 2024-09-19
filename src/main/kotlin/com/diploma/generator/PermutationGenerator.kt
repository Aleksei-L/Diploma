package com.diploma.generator

import com.diploma.data.Permutation

class PermutationGenerator {
	/**
	 * Функция генерации всех `n!` перестановок
	 *
	 * Перестановка будет содержать элементы от `0` до `n-1` включительно,
	 * но так как основное её применение - передача на вход исполнителю,
	 * где она играет роль индексов в массиве задач, то такое поведение
	 * является приемлемым
	 *
	 * [taskNumber] - кол-во задач для исполнителя (`n`)
	 */
	fun generatePermutations(taskNumber: Int): Set<Permutation> {
		val result = mutableSetOf<Permutation>()
		val elements = (0..<taskNumber).toMutableList()
		val indices = IntArray(taskNumber) { 0 }

		result.add(Permutation(elements.toList()))

		var i = 0
		while (i < taskNumber) {
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
}
