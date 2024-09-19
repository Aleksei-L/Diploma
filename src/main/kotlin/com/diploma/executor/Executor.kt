package com.diploma.executor

import com.diploma.data.Permutation
import com.diploma.data.Task

/**
 * Класс представляющий исполнителя
 *
 * TODO
 */
class Executor {
	/**
	 * Функция исполнения одной задачи исполнителем
	 *
	 * Возвращает пару `(x_i, y_i)`, которая характеризует
	 * момент начала и момент завершения работы
	 */
	private fun executeTask(task: Task): Pair<Int, Int> {
		if (time < task.startTime)
			time = task.startTime

		val startPoint = time
		val endPoint = time + task.time
		time = endPoint

		return Pair(startPoint, endPoint)
	}

	/**
	 * Функция исполнения нескольких задач по заданной перестановке исполнителем
	 *
	 * Возвращает список пар `(x_i, y_i)`, каждая из которых
	 * характеризует момент начала и момент завершения работы
	 */
	fun executeTasksByPermutation(tasks: List<Task>, permutation: Permutation): List<Pair<Int, Int>> {
		val result = mutableListOf<Pair<Int, Int>>()

		for (i in permutation)
			result.addLast(executeTask(tasks[i]))
		time = 0

		return result
	}

	/**
	 * Шкала тактов времени у исполнителя
	 *
	 * Может измениться только внутри класса, причём функция
	 * [executeTask] только увеличивает время, а функция
	 * [executeTasksByPermutation] только обнуляет время
	 */
	companion object {
		var time = 0
			private set
	}
}
