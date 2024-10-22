package com.diploma.solver

import com.diploma.data.Task
import com.diploma.executor.ExecutorWrapper
import com.diploma.generator.NeighborsGenerator
import com.diploma.generator.SinglePermutationGenerator

/**
 * Метод решения задачи теории расписаний при помощи
 * hill-climbing алгоритма
 *
 * Случайным образом сгенерирует начальную перестановку,
 * на каждом шаге сгенерирует `n` штук соседей этой
 * перестановки и оценит штраф для них. При нахождении
 * среди соседей перестановки с меньшим штрафом примет её
 * за начальную и перейдёт на следующую итерацию.
 * Остановится, как только среди соседей не будет перестановки
 * со штрафом меньшим чем текущий
 *
 * Выведет в консоль перестановку, на которой был получен
 * минимальный штраф и значение штрафа
 */
class HillClimbing(
	private val executorWrapper: ExecutorWrapper,
	private val neighborsGenerator: NeighborsGenerator,
	private val singlePermutationGenerator: SinglePermutationGenerator
) : Solver {
	override fun solve(tasks: List<Task>): Int {
		var permutation = singlePermutationGenerator.singlePermutation
		var neighbors = neighborsGenerator.generateNeighbors(permutation)
		var minim = Int.MAX_VALUE

		while (true) {
			var fineHasChanged = false

			for (i in neighbors) {
				val fine = executorWrapper.executeByPermutationWithFines(tasks, i)
				if (fine < minim) {
					minim = fine
					permutation = i
					fineHasChanged = true
				}
			}

			if (!fineHasChanged) {
				println("Решение методом восхождения на холм:")
				println("Перестановка: $permutation, штраф: $minim\n")
				break
			}

			neighbors = neighborsGenerator.generateNeighbors(permutation)
		}

		return minim
	}
}
