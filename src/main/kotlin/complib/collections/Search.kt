package complib.collections

fun <T : Comparable<T>> List<T?>.lowerBound(
    element: T,
    fromIndex: Int = 0,
    toIndex: Int = size
): Int {
    var low = fromIndex
    var high = toIndex
    while (low < high) {
        val mid = (low + high) / 2
        if (element <= this.get(mid)!!) {
            high = mid
        } else {
            low = mid + 1
        }
    }
    return low
}

fun <T : Comparable<T>> List<T?>.upperBound(
    element: T,
    fromIndex: Int = 0,
    toIndex: Int = size
): Int {
    var low = fromIndex
    var high = toIndex
    while (low < high) {
        val mid = (low + high) / 2
        if (element >= this.get(mid)!!) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    return low
}

fun binarySearch(range: Pair<Int, Int>, f: (Int) -> Boolean): Int {
    // search y such that `f(x) == if(x < y) false else true`
    var (low, high) = range
    while (low < high) {
        val mid = (low + high) / 2
        if (f(mid)) {
            high = mid
        } else {
            low = mid + 1
        }
    }
    return low
}
