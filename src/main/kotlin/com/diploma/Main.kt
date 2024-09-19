package com.diploma

import com.diploma.executor.Executor
import com.diploma.generator.PermutationGenerator
import com.diploma.generator.TaskGenerator

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
 * 1) Полный перебор `n!` перестановок O(n!)
 * Критерий нарушения D зависит от Y
 * Минимизируем критерий => находим самую лучшую перестановку
 * Можем не получить решение удовлетворяющее всем ограничениям (называется допустимым) - Тогда уберём 3 ограничение
 * D - вообще говоря должно генерироваться по нормальному распределению
 * 2) Эвристики (например восхождение на холм) O(n^2)
 * Для исходных данных генерируется "Коэффициент" (например (d+D)/t )
 * Сортируем по убыванию => получаем перестановку
 *
 * Считаем относительное отклонение Д(ельта), например = (F^эвр - F^0)/F^0
 */
fun main() {
	val executor by lazy { Executor() }
	val taskGenerator by lazy { TaskGenerator() }
	val permutationGenerator by lazy { PermutationGenerator() }

	val tasks = taskGenerator.generateTasks(
		3,
		1..100,
		5..10,
		2..5
	)
	val permutations = permutationGenerator.generatePermutations(3)

	println(permutations)
	tasks.forEach {
		println(it)
	}
	println()

	var counter = 1
	for (i in permutations) {
		val arr = executor.executeTasksByPermutation(tasks, i)
		println("step: ${counter++}: $arr")
	}
}
