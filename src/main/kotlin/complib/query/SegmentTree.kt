package complib.query

import complib.math.Monoid

class SegmentTree<T>(array: Array<T>, private val monoid: Monoid<T>) {
    private val n = array.size
    private val tree = MutableList(2 * n) {
        if (it >= n) array[it - n] else monoid.empty()
    }

    init {
        for (i in n - 1 downTo 0) {
            tree[i] = monoid.combine(tree[2 * i], tree[2 * i + 1])
        }
    }

    fun get(index: Int) = tree[index + n]
    fun query(left: Int, right: Int): T {
        // [left, right)
        require(left in (0 until right) && right <= n) {
            "Invalid Range [$left, $right)"
        }
        var (tl, tr) = left + n to right + n
        var ret = monoid.empty()
        while (tl < tr) {
            if (tl % 2 == 1) {
                ret = monoid.combine(ret, tree[tl])
                tl++
            }
            if (tr % 2 == 1) {
                tr--
                ret = monoid.combine(ret, tree[tr])
            }
            tl /= 2
            tr /= 2
        }
        return ret
    }

    fun update(index: Int, value: T) {
        var tIndex = index + n
        tree[tIndex] = value
        while (tIndex > 1) {
            tIndex /= 2
            tree[tIndex] = monoid.combine(tree[2 * tIndex], tree[2 * tIndex + 1])
        }
    }
}
