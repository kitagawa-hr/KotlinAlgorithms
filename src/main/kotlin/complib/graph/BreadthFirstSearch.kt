package complib.graph

import java.util.ArrayDeque

inline fun <reified T : Comparable<T>, reified E : Comparable<E>> distancesFrom(
    graph: Graph<T, E>,
    from: Int,
    empty: E,
    combine: (E, E) -> E
): Array<E?> {
    val distances = Array<E?>(graph.size) { null }
    val deque = ArrayDeque<Int>()
    distances[from] = empty
    deque.add(from)
    while (deque.size > 0) {
        val node = deque.removeFirst()
        val currentDist = distances[node]!!
        graph.edgesFrom(node).filter { distances[it.to] == null }.forEach {
            distances[it.to] = combine(currentDist, it.weight)
            deque.add(it.to)
        }
    }
    return distances
}

inline fun <reified T : Comparable<T>, reified E : Comparable<E>> shortestPath(
    graph: Graph<T, E>,
    from: Int,
    to: Int
): List<Int> {
    val beforeNodes = IntArray(graph.size) { -1 }
    val deque = ArrayDeque<Int>()
    deque.add(from)
    while (deque.size > 0) {
        val node = deque.removeFirst()
        if (node == to) {
            val path = ArrayDeque<Int>()
            var current = to
            path.add(to)
            while (current != from) {
                current = beforeNodes[current]
                path.addFirst(current)
            }
            return path.toList()
        }
        graph.edgesFrom(node).filter { beforeNodes[it.to] == -1 }.forEach {
            beforeNodes[it.to] = node
            deque.add(it.to)
        }
    }
    return emptyList()
}
