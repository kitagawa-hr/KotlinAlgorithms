package complib.collections

fun List<Int>.dot(other: List<Int>): Int =
        this.mapIndexed { index, value -> value * other[index] }.sum()

fun List<Long>.dot(other: List<Long>): Long =
        this.mapIndexed { index, value -> value * other[index] }.sum()


fun <T> product(vararg lists: List<T>): Sequence<List<T>> {
    fun <T> productTwoList(left: List<T>, right: List<T>): Sequence<List<T>> =
            left.asSequence().flatMap { leftElem -> right.asSequence().map { listOf(leftElem, it) } }

    fun <T> product2DListAnd1DList(list2D: Sequence<List<T>>, list1D: List<T>): Sequence<List<T>> =
            list2D.flatMap { seq -> list1D.asSequence().map { seq + it } }

    require(lists.size >= 2)
    return lists.asSequence()
            .drop(2)
            .fold(productTwoList(lists[0], lists[1])) { acc, suc -> product2DListAnd1DList(acc, suc) }
}

fun <T> Collection<T>.powerSet(): Set<Set<T>> = powerSet(this, setOf(setOf()))

private tailrec fun <T> powerSet(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    left.isEmpty() -> acc
    else -> powerSet(left.drop(1), acc + acc.map { it + left.first() })
}

fun <T> Set<T>.combinations(n: Int): Set<Set<T>> = when {
    n < 0 -> throw Error("n must be positive, but got $n")
    n == 0 -> setOf(setOf())
    n >= size -> setOf(toSet())
    else -> powerSet()
            .filter { it.size == n }
            .toSet()
}

fun <T> List<T>.insert(index: Int, element: T): List<T> = when {
    index < 0 || index > size -> throw Error("Can not insert to index = $index")
    index == 0 -> listOf(element) + this
    index == size -> this + element
    else -> dropLast(size - index) + element + drop(index)
}

fun <T> List<T>.permutations(): Set<List<T>> = when {
    isEmpty() -> setOf()
    size == 1 -> setOf(listOf(get(0)))
    else -> {
        val element = get(0)
        drop(1).permutations()
                .flatMap { sublist -> (0..sublist.size).map { i -> sublist.insert(i, element) } }
                .toSet()
    }
}

fun <T, R> Iterable<T>.scanLeft(initial: R, operation: (acc: R, T) -> R): Sequence<R> {
    var last = initial
    return this.asSequence().map { last = operation(last, it); last }
}
