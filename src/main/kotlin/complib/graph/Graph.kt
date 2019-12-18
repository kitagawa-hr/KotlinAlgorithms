package complib.graph

data class Vertex<T : Comparable<T>, E : Comparable<E>>(val data: T? = null) {
    data class Edge<T : Comparable<T>>(val to: Int, val weight: T? = null)

    val edges = mutableListOf<Edge<E>>()

    fun addEdge(to: Int, weight: E?) = when (edges.find { it.to == to }) {
        null -> {
            edges.add(Edge(to, weight))
            true
        }
        else -> false
    }

    fun removeEdge(to: Int) = edges.removeIf { it.to == to }
}

class AdjacentListGraph<T : Comparable<T>, E : Comparable<E>>(
    val vertices: MutableList<Vertex<T, E>>
) {
    fun getWeight(from: Int, to: Int): E? = vertices[from].edges.find { it.to == to }?.weight
    fun addEdge(from: Int, to: Int, weight: E? = null) = vertices[from].addEdge(to, weight)
    fun removeEdge(from: Int, to: Int) = vertices[from].removeEdge(to)
    fun isAdjacent(from: Int, to: Int) = vertices[from].edges.find { it.to == to } != null
}
