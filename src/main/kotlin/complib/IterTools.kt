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

fun <T> product(vararg lists: List<T>): List<List<T>> {
    fun <T> oneDimTimesOne(left: List<T>, right: List<T>): List<List<T>> =
        left.flatMap { lelem -> right.map { relem -> listOf(lelem, relem) } }

    fun <T> twoDimTimesOne(left: List<List<T>>, right: List<T>): List<List<T>> {
        val product = mutableListOf<List<T>>()
        for (list in left) {
            for (element in right) {
                product.add(list + listOf(element))
            }
        }
        return product
    }
    require(lists.size >= 2)
    return lists
        .drop(2)
        .fold(oneDimTimesOne(lists[0], lists[1])) { acc, suc -> twoDimTimesOne(acc, suc) }
}
