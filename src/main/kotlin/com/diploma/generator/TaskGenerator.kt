package com.diploma.generator

import com.diploma.data.Task
import com.diploma.util.GaussArgument
import java.util.*

class TaskGenerator {
	/**
	 * Сквозной номер задачи
	 *
	 * Может изменяться только внутри класса при генерации перестановок в функции [generateTasks]
	 */
	private var number = 1

	/**
	 * Функция генерации списка из n задач для исполнителя
	 *
	 * - [taskNumber] - кол-во задач для исполнителя
	 * - [startTimeRange] - диапазон времени начала доступности задачи
	 * - [endTimeRange] - диапазон времени желаемого завершения задачи
	 * - [timeRange] - диапазон времени исполнения задачи
	 * - [fineRange] - диапазон штрафа
	 */
	fun generateTasks(
		taskNumber: Int,
		startTimeRange: IntRange,
		endTimeRange: IntRange,
		timeRange: IntRange,
		fineRange: IntRange
	): List<Task> {
		val tasksList = mutableListOf<Task>()

		for (i in 0..<taskNumber) {
			/**
			 * Генерация времени начала выполнения задачи до тех пор, пока оно не попадёт в отрезок [startTimeRange]
			 */
			var startTime: Int
			do {
				startTime = Random().nextGaussian(
					((startTimeRange.first + startTimeRange.last) / 2).toDouble(),
					GaussArgument.SIGMA
				).toInt()
			} while (startTime < startTimeRange.first || startTime > startTimeRange.last)

			/**
			 * Генерация времени окончания выполнения задачи до тех пор, пока оно не попадёт в отрезок [endTimeRange]
			 */
			var endTime: Int
			do {
				endTime = Random().nextGaussian(
					((endTimeRange.first + endTimeRange.last) / 2).toDouble(),
					GaussArgument.SIGMA
				).toInt()
			} while (endTime < endTimeRange.first || endTime > endTimeRange.last)

			/**
			 * Генерация времени выполнения задачи до тех пор, пока оно не попадёт в отрезок [timeRange]
			 */
			var time: Int
			do {
				time = Random().nextGaussian(
					((timeRange.first + timeRange.last) / 2).toDouble(),
					GaussArgument.SIGMA
				).toInt()
			} while (time < timeRange.first || time > timeRange.last)

			/**
			 * Генерация штрафа за превышение времени выполнения
			 * задачи до тех пор, пока он не попадёт в отрезок [fineRange]
			 */
			var fine: Int
			do {
				fine = Random().nextGaussian(
					((fineRange.first + fineRange.last) / 2).toDouble(),
					GaussArgument.SIGMA
				).toInt()
			} while (fine < fineRange.first || fine > fineRange.last)

			tasksList.addLast(
				Task(
					number++,
					startTime,
					endTime,
					time,
					fine
				)
			)
		}

		return tasksList
	}
}
