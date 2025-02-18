package com.diploma.shared

import com.diploma.generator.TaskGenerator
import com.diploma.util.TASK_NUMBER

/**
 * TODO
 */
object Tasks {
	private val taskGenerator = TaskGenerator()
	val tasks = taskGenerator.generateTasks(
		TASK_NUMBER,
		1..10,
		20..40,
		7..17,
		5..10
	)
}
