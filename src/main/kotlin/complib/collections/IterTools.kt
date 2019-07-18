package complib.collections

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

fun List<Int>.dot(other: List<Int>): Int =
        this.mapIndexed { index, value -> value * other[index] }.sum()

fun List<Long>.dot(other: List<Long>): Long =
        this.mapIndexed { index, value -> value * other[index] }.sum()

