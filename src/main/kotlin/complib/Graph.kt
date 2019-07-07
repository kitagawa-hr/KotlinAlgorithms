package complib


class UnionFind(n: Int) {
    data class Node(var parent: Int, var size: Int)
    val nodes = Array<Node>(n) { Node(parent = it, size = 1) }
    fun findRoot(x: Int): Int {
        if (x == this.nodes[x].parent) return x
        nodes[x].parent = findRoot(nodes[x].parent)
        return nodes[x].parent
    }
    fun unite(x: Int, y: Int) {
        var xRoot = findRoot(x)
        var yRoot = findRoot(y)
        if (xRoot == yRoot) return
        if (getSize(xRoot) < getSize(yRoot)) {
            nodes[xRoot].parent = yRoot
            nodes[yRoot].size += nodes[xRoot].size
        } else {
            nodes[yRoot].parent = xRoot
            nodes[xRoot].size += nodes[yRoot].size
        }
    }
    fun getSize(x: Int) = nodes[findRoot(x)].size
    fun isSame(x: Int, y: Int) = findRoot(x) == findRoot(y)
}
