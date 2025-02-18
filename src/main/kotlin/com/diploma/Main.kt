package com.diploma

import com.diploma.executor.ExecutorWrapper
import com.diploma.shared.Tasks.tasks
import com.diploma.solver.CompleteEnumeration
import com.diploma.solver.EGA
import com.diploma.solver.HillClimbing
import kotlin.system.measureTimeMillis

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
 * 1. Полный перебор `n!` перестановок
 * 2. Эвристики (например восхождение на холм)
 * 3. ЭГА
 *
 * Для оценки решения задачи считаем относительное отклонение: `(F^эвр - F^0)/F^0`
 */
fun main() {
	val executorWrapper = ExecutorWrapper()

	val solverList = listOf(
		CompleteEnumeration(executorWrapper),
		HillClimbing(executorWrapper),
		EGA()
	)
	val finesList = mutableListOf<Int>()

	for (i in solverList.indices) {
		val executionTime: Long = measureTimeMillis {
			finesList += solverList[i].solve(tasks)
		}
		println("Время исполнения: ${executionTime.toFloat() / 1000f} с.\n")
	}

	println("Относительное отклонение: ${"%.4f".format((finesList[1] - finesList[0]).toDouble() / finesList[0])}")
}
