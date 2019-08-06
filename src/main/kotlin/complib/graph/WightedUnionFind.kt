package complib.graph


class WeightedUnionFind(n: Int) {
    data class Node(var parent: Int, var size: Int, var weight: Long)

    private val nodes = Array(n) { Node(parent = it, size = 1, weight = 0) }
    fun findRoot(x: Int): Int {
        if (x == this.nodes[x].parent) return x
        val root = findRoot(nodes[x].parent)
        nodes[x].weight += nodes[nodes[x].parent].weight
        nodes[x].parent = root
        return root
    }

    fun unite(x: Int, y: Int, weight: Long): Boolean {
        // nodes[x].weight + weight = nodes[y].weightとなるようにする
        val xRoot = findRoot(x)
        val yRoot = findRoot(y)
        if (xRoot == yRoot) return false
        val (smaller, bigger, newWeight) = when (getSize(xRoot) >= getSize(yRoot)) {
            true -> Triple(yRoot, xRoot, nodes[x].weight - nodes[y].weight + weight)
            else -> Triple(xRoot, yRoot, nodes[y].weight - nodes[x].weight - weight)
        }
        nodes[smaller].parent = bigger
        nodes[bigger].size += nodes[smaller].size
        nodes[smaller].weight = newWeight
        return true
    }

    fun getWeight(x: Int): Long {
        findRoot(x)
        return nodes[x].weight
    }

    fun getDiff(x: Int, y: Int) = getWeight(y) - getWeight(x)
    fun getSize(x: Int) = nodes[findRoot(x)].size
    fun isSame(x: Int, y: Int) = findRoot(x) == findRoot(y)
}
