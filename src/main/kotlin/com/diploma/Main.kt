package com.diploma

import com.diploma.ega.betaTournament
import com.diploma.ega.getInitialPopulationByHillClimbing
import com.diploma.executor.ExecutorWrapper
import com.diploma.shared.Tasks.tasks
import com.diploma.solver.CompleteEnumeration
import com.diploma.solver.EGA
import com.diploma.solver.HillClimbing
import com.diploma.util.POPULATION_SIZE
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
		EGA(::betaTournament),
		EGA(::betaTournament, getInitialPopulationByHillClimbing(executorWrapper))
	)
	val finesList = mutableListOf<Int>()
	var hillClimbingTime = 0L

	for (i in solverList.indices) {
		val executionTime = measureTimeMillis {
			finesList.addLast(solverList[i].solve(tasks))
		}
		if (i == 1)
			hillClimbingTime = executionTime
		println(
			"Время исполнения: ${
				if (i == 3)
					(hillClimbingTime * POPULATION_SIZE + executionTime.toFloat()) / 1000f
				else
					executionTime.toFloat() / 1000f
			} с."
		)
		println("Относительное отклонение: ${"%.4f".format((finesList[i] - finesList[0]).toDouble() / finesList[0])}\n")
	}
}
