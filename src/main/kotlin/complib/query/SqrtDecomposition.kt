package complib.query

import complib.math.Monoid

class SqrtDecomposition<T>(private val array: Array<T>, private val monoid: Monoid<T>) {
    private val divSize = Math.sqrt(array.size.toDouble()).toInt()
    private val bucket = (0 until divSize)
        .map {
            array.slice(it * divSize until (it + 1) * divSize)
                .reduce { a, b -> monoid.combine(a, b) }
        }
        .toMutableList()

    init {
        require(divSize * divSize == array.size) {
            "array size must be square number. size: ${array.size}"
        }
    }

    fun get(index: Int) = array[index]
    fun query(left: Int, right: Int): T {
        // [left, right)
        require(left in (0 until right) && right <= array.size) {
            "Invalid Range [$left, $right)"
        }
        val bl = left / divSize
        val br = right / divSize
        var res = monoid.empty()
        if (bl == br) {
            (left until right).forEach { res = monoid.combine(res, array[it]) }
        } else {
            (left until (bl + 1) * divSize).forEach { res = monoid.combine(res, array[it]) }
            (bl + 1 until br).forEach { res = monoid.combine(res, bucket[it]) }
            (br * divSize until right).forEach { res = monoid.combine(res, array[it]) }
        }
        return res
    }

    fun update(index: Int, value: T) {
        array[index] = value
        val bi = index / divSize
        updateBucket(bi)
    }

    private fun updateBucket(bIndex: Int) {
        bucket[bIndex] = array.slice(bIndex * divSize until (bIndex + 1) * divSize)
            .reduce { a, b -> monoid.combine(a, b) }
    }

    fun updateRange(left: Int, right: Int, value: T) {
        // [left, right)
        require(left in (0 until right) && right <= array.size) {
            "Invalid Range [$left, $right)"
        }
        (left until right).forEach { array[it] = value }
        val bl = left / divSize
        val br = right / divSize
        (bl..br).forEach { updateBucket(it) }
    }
}
