package complib

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
