package com.diploma.solver

import com.diploma.data.Task
import com.diploma.executor.ExecutorWrapper
import com.diploma.generator.generatePermutations

/**
 * Метод решения задачи теории расписаний полным перебором.
 *
 * Построит `n!` всех перестановок из `n` задач, выполнит
 * каждую из них при помощи исполнителя и подсчитает
 * суммарный штраф
 *
 * Выведет в консоль перестановку, на которой был получен
 * минимальный штраф и значение штрафа
 */
class CompleteEnumeration(
	private val executorWrapper: ExecutorWrapper
) : Solver {
	override fun solve(tasks: List<Task>): Int {
		val permutations = generatePermutations()
		val result = executorWrapper.executeByAllPermutationsWithFines(tasks, permutations)
		println("Решение полным перебором:")
		println("Перестановка: ${result.first}, штраф: ${result.second}")
		return result.second
	}
}
