package com.diploma

import com.diploma.executor.ExecutorWrapper
import com.diploma.generator.PermutationGenerator
import com.diploma.generator.TaskGenerator
import com.diploma.util.TaskNumber.TASK_NUMBER

/**
 * Задача теории расписаний с одним исполнителем
 * - `n` - кол-во задач
 * - `d_i` - время, когда `i`-тая задача становится доступна исполнителю
 * - `D_i` - время, к которому `i`-тая задача должна закончиться
 * - `t_i` - время обработки `i`-той задачи
 * - `c_i` - штраф за превышение времени `D_i` `i`-той задачи
 *
 * Модель:
 * - `x_i` - момент времени начала выполнения `i`-той задачи
 * - `y_i` - момент времени конца выполнения `i`-той задачи
 *
 * Ограничения:
 * - `y_i = x_i + t_i` - работы выполняются без прерываний
 * - `x_i >= d_i` - работа не может быть исполнена раньше чем стала доступна исполнителю
 * - `y_i <= D_i` - работа не может закончиться позже установленного времени `D_i`
 * - `x_i >= y_j` ИЛИ `x_j >= y_i`, `i = 1...n` - исполнитель в один момент времени может выполнять только одну работу
 *
 * Как решать:
 * 1. Полный перебор `n!` перестановок `O(n!)`
 * 2. Эвристики (например восхождение на холм) `O(n^2)`
 * 3. ЭГА
 *
 * Считаем относительное отклонение Д(ельта), например = (F^эвр - F^0)/F^0
 */
fun main() {
	val executorWrapper by lazy { ExecutorWrapper() }
	val taskGenerator by lazy { TaskGenerator() }
	val permutationGenerator by lazy { PermutationGenerator() }

	val tasks = taskGenerator.generateTasks(
		TASK_NUMBER,
		1..10,
		20..40,
		7..17,
		5..10
	)
	val permutations = permutationGenerator.generatePermutations(TASK_NUMBER)

	println(executorWrapper.executeAllWithFines(tasks, permutations))
}
