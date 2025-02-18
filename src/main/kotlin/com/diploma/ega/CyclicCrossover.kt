package com.diploma.ega

import com.diploma.data.Permutation
import com.diploma.util.contains

/**
 * TODO
 */
fun cyclicCrossover(parentOne: Permutation, parentTwo: Permutation): Permutation {
	val listParentOne = parentOne.numbersList
	val listParentTwo = parentTwo.numbersList
	val cyclicList = mutableListOf<List<Int>>()

	for (beginIndex in listParentOne.indices) {
		var mBeginIndex = beginIndex
		val beginItem = listParentOne[mBeginIndex]
		if (cyclicList.contains(beginItem))
			continue
		val cycle = mutableListOf(beginItem)
		while (true) {
			val secondItem = listParentTwo[mBeginIndex]
			if (secondItem == beginItem)
				break
			cycle.addLast(secondItem)
			mBeginIndex = listParentOne.indexOf(secondItem)
		}
		cyclicList.addLast(cycle)
	}

	return Permutation(cyclicList.flatten())
}
