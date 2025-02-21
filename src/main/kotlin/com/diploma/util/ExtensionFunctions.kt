package com.diploma.util

/**
 * Функция-расширение для поиска значения [value] в
 * двумерном списке
 *
 * Возвращает `true` если элемент был найден и
 * `false` иначе
 */
fun List<List<Int>>.contains(value: Int): Boolean {
	for (list in this) {
		for (element in list) {
			if (element == value)
				return true
		}
	}
	return false
}

/**
 * Функция-расширение для возврата [count] случайных
 * элементов из списка
 *
 * Возвращает элементы в виде списка
 */
fun <T> List<T>.random(count: Int): List<T> {
	val mList = this.toMutableList()
	val selected = mutableListOf<T>()

	for (i in 0..<count) {
		mList.shuffle()
		val item = mList.random()
		selected.addLast(item)
		mList.remove(item)
	}

	return selected
}
