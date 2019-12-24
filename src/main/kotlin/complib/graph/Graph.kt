package complib.graph

interface Graph<T : Comparable<T>, E : Comparable<E>> {
    val size: Int
    fun getWeight(from: Int, to: Int): E?
    fun edgesFrom(from: Int): List<Edge<E>>
    fun addEdge(from: Int, to: Int, weight: E): Boolean
    fun removeEdge(from: Int, to: Int): Boolean
    fun isAdjacent(from: Int, to: Int): Boolean
}

data class Edge<T : Comparable<T>>(val to: Int, val weight: T)
data class Vertex<T : Comparable<T>, E : Comparable<E>>(val data: T) {
    val edges = mutableListOf<Edge<E>>()

    fun addEdge(to: Int, weight: E) = when (edges.find { it.to == to }) {
        null -> {
            edges.add(Edge(to, weight))
            true
        }
        else -> false
    }

    fun removeEdge(to: Int): Boolean {
        for ((i, edge) in edges.withIndex()) {
            if (edge.to == to) {
                edges.removeAt(i)
                return true
            }
        }
        return false
    }
}

class AdjacentListGraph<T : Comparable<T>, E : Comparable<E>>(
    private val vertices: MutableList<Vertex<T, E>>
) : Graph<T, E> {
    override val size = vertices.size
    override fun getWeight(from: Int, to: Int): E? =
        vertices[from].edges.find { it.to == to }?.weight
    override fun edgesFrom(from: Int): List<Edge<E>> = vertices[from].edges
    override fun addEdge(from: Int, to: Int, weight: E) = vertices[from].addEdge(to, weight)
    override fun removeEdge(from: Int, to: Int) = vertices[from].removeEdge(to)
    override fun isAdjacent(from: Int, to: Int) =
        vertices[from].edges.find { it.to == to } != null
}

class AdjacencyMatrixGraph<T : Comparable<T>, E : Comparable<E>>(
    private val matrix: Array<Array<E?>>
) : Graph<T, E> {
    override val size = matrix.size
    override fun getWeight(from: Int, to: Int): E? = matrix[from][to]
    override fun edgesFrom(from: Int): List<Edge<E>> =
        matrix[from].withIndex()
            .filter { it.value != null }
            .map { Edge(it.index, it.value!!) }

    override fun addEdge(from: Int, to: Int, weight: E): Boolean {
        if (matrix[from][to] == null) {
            matrix[from][to] = weight
            return true
        }
        return false
    }

    override fun removeEdge(from: Int, to: Int): Boolean {
        if (from >= size || to >= size || matrix[from][to] == null) {
            return false
        }
        matrix[from][to] = null
        return true
    }

    override fun isAdjacent(from: Int, to: Int): Boolean = matrix[from][to] != null
}
