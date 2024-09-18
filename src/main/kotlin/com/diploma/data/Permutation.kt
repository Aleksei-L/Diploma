package com.diploma.data

/**
 * Класс представляющий перестановку из n элементов
 *
 * [numbersList] - список, хранящий n элементов
 */
data class Permutation(val numbersList: List<Int>) {
	override fun toString(): String {
		var res = "("
		for (i in 0..<numbersList.size - 1)
			res += "${numbersList[i]}, "
		res += "${numbersList[numbersList.size - 1]})"
		return res
	}
}
