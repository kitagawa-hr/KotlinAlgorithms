package complib

class UnionFind(n: Int) {
    data class Node(var parent: Int, var size: Int)
    private val nodes = Array<Node>(n) { Node(parent = it, size = 1) }
    fun findRoot(x: Int): Int {
        if (x == this.nodes[x].parent) return x
        nodes[x].parent = findRoot(nodes[x].parent)
        return nodes[x].parent
    }
    fun unite(x: Int, y: Int): Boolean {
        val xRoot = findRoot(x)
        val yRoot = findRoot(y)
        if (xRoot == yRoot) return false
        val (smaller, bigger) = if (getSize(xRoot) < getSize(yRoot)) {
            Pair(xRoot, yRoot)
        } else { Pair(yRoot, xRoot) }
        nodes[smaller].parent = bigger
        nodes[bigger].size += nodes[smaller].size
        return true
    }
    fun getSize(x: Int) = nodes[findRoot(x)].size
    fun isSame(x: Int, y: Int) = findRoot(x) == findRoot(y)
}

class WeightedUnionFind(n: Int) {
    data class Node(var parent: Int, var size: Int, var weight_diff: Long)
    private val nodes = Array<Node>(n) { Node(parent = it, size = 1, weight_diff = 0) }
    fun findRoot(x: Int): Int {
        if (x == this.nodes[x].parent) return x
        nodes[x].parent = findRoot(nodes[x].parent)
        nodes[x].weight_diff += nodes[nodes[x].parent].weight_diff
        return nodes[x].parent
    }
    fun unite(x: Int, y: Int, weight: Long): Boolean {
        val xRoot = findRoot(x)
        val yRoot = findRoot(y)
        if (xRoot == yRoot) return false
        val (smaller, bigger) = if (getSize(xRoot) < getSize(yRoot)) {
            Pair(xRoot, yRoot)
        } else { Pair(yRoot, xRoot) }
        val new_weight = weight + getWeight(xRoot) - getWeight(yRoot)
        nodes[smaller].parent = bigger
        nodes[bigger].size += nodes[smaller].size
        nodes[smaller].weight_diff = if (smaller == xRoot) -new_weight else new_weight
        return true
    }
    fun getWeight(x: Int): Long {
        findRoot(x)
        return nodes[x].weight_diff
    }
    fun getDiff(x: Int, y: Int) = getWeight(y) - getWeight(x)
    fun getSize(x: Int) = nodes[findRoot(x)].size
    fun isSame(x: Int, y: Int) = findRoot(x) == findRoot(y)
}
