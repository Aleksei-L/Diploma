package com.diploma.solver

import com.diploma.data.Task

/**
 * Интерфейс, который реализуют все классы являющиеся
 * способами решения задачи теории расписаний. Содержит
 * метод [solve], благодаря которому и выполняется логика
 * решения задачи.
 *
 * Каждый из методов решения задачи должен выводить
 * в консоль сопутствующие результаты вычислений
 */
interface Solver {
	/**
	 * Метод принимает список задач [tasks], которые
	 * необходимо исполнить и возвращает суммарный
	 * штраф, полученный при выполнении задач
	 */
	fun solve(tasks: List<Task>): Int
}
