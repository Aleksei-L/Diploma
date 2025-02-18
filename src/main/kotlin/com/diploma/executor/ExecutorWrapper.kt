package com.diploma.executor

import com.diploma.data.Permutation
import com.diploma.data.Task
import kotlin.math.max

/**
 * Обёртка над исполнителем, для отслеживания за
 * нарушениями желаемых времён исполнения задач
 */
class ExecutorWrapper {
	private val executor = Executor()

	/**
	 * Функция подсчёта суммарного штрафа для заданных списка
	 * задач и перестановки
	 *
	 * Возвращает штраф
	 */
	fun executeByPermutationWithFines(
		tasks: List<Task>,
		permutation: Permutation
	): Int {
		val result = executor.executeTasksByPermutation(tasks, permutation)
		var fineSum = 0
		var index = 0

		for (i in permutation)
			fineSum += tasks[i].fine * max(0, result[index++].second - tasks[i].endTime)

		return fineSum
	}

	/**
	 * Функция нахождения такого порядка выполнения задач,
	 * при котором достигается наименьший штраф. Подаёт список
	 * задач и перестановок на вход исполнителю, вычисляет штраф
	 * для каждой перестановки и определяет наименьший.
	 *
	 * Возвращает пару из перестановки, на которой достигнут
	 * наименьший штраф, и штрафа
	 */
	fun executeByAllPermutationsWithFines(
		tasks: List<Task>,
		permutations: List<Permutation>
	): Pair<Permutation, Int> {
		val map = executor.executeTasksByPermutations(tasks, permutations)
		var fineSumMinim = Int.MAX_VALUE
		lateinit var result: Pair<Permutation, Int>

		for ((permutation, pairs) in map) {
			var fineSum = 0
			for ((index, j) in permutation.withIndex()) {
				val maxim = max(0, pairs[index].second - tasks[j].endTime)
				fineSum += tasks[j].fine * maxim
			}
			if (fineSum < fineSumMinim) {
				fineSumMinim = fineSum
				result = Pair(permutation, fineSum)
			}
		}

		return result
	}
}
