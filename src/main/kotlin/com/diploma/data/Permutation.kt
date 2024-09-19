package com.diploma.data

/**
 * Класс представляющий перестановку из n элементов
 *
 * Благодаря реализации интерфейса [Iterable] по перестановке
 * можно итерироваться, так как будет выдан итератор списка [numbersList]
 *
 * [numbersList] - список, хранящий n элементов
 */
data class Permutation(val numbersList: List<Int>) : Iterable<Int> {
	override fun toString(): String {
		var res = "("
		for (i in 0..<numbersList.size - 1)
			res += "${numbersList[i]}, "
		res += "${numbersList[numbersList.size - 1]})"
		return res
	}

	override fun iterator(): Iterator<Int> = numbersList.iterator()
}
