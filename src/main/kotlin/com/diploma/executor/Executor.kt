package com.diploma.executor

import com.diploma.data.Permutation
import com.diploma.data.Task

/**
 * Класс представляющий исполнителя для исполнения
 * списка задач в соответствии со списком перестановок.
 * Оба списка подаются на вход исполнителю
 *
 * Содержит внутри себя абстракцию для времени, в виде
 * переменной [time], благодаря которой ведётся наблюдение
 * за временами исполнения каждой из задач
 */
class Executor {
	/**
	 * Функция исполнения одной задачи
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
	 * Функция исполнения всех задач по одной заданной перестановке
	 *
	 * Возвращает список пар `(x_i, y_i)`, каждая из которых
	 * характеризует момент начала и момент завершения работы
	 */
	private fun executeTasksByPermutation(tasks: List<Task>, permutation: Permutation): List<Pair<Int, Int>> {
		val result = mutableListOf<Pair<Int, Int>>()

		for (i in permutation)
			result.addLast(executeTask(tasks[i]))
		time = 0

		return result
	}

	/**
	 * Функция исполнения всех задач по всем заданным перестановкам
	 *
	 * Возвращает ассоциативный массив, где ключом выступает перестановка,
	 * а значением - список пар `(x_i, y_i)` для этой перестановки,
	 * каждая из которых характеризует момент начала и момент завершения работы
	 */
	fun executeTasksByPermutations(
		tasks: List<Task>,
		permutations: List<Permutation>
	): Map<Permutation, List<Pair<Int, Int>>> {
		val result = mutableMapOf<Permutation, List<Pair<Int, Int>>>()

		for (i in permutations)
			result[i] = executeTasksByPermutation(tasks, i)

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
