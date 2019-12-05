package complib.graph

class UnionFind(n: Int) {
    data class Node(var parent: Int, var size: Int)

    private val nodes = Array(n) { Node(parent = it, size = 1) }
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
        } else {
            Pair(yRoot, xRoot)
        }
        nodes[smaller].parent = bigger
        nodes[bigger].size += nodes[smaller].size
        return true
    }

    fun getSize(x: Int) = nodes[findRoot(x)].size
    fun isSame(x: Int, y: Int) = findRoot(x) == findRoot(y)
}
