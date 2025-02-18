package com.diploma.util

import com.diploma.data.Individual

/**
 * TODO
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
 * TODO
 */
fun List<Individual>.random(count: Int): List<Individual> {
	val mList = this.toMutableList()
	val selected = mutableListOf<Individual>()

	for (i in 0..<count) {
		mList.shuffle()
		val item = mList.random()
		selected.addLast(item)
		mList.remove(item)
	}

	return selected
}
