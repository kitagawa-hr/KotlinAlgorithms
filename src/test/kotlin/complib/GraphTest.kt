package complib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UnionFindTests {

    @Test
    fun uniteTest() {
        val uf = UnionFind(10)
        uf.unite(1, 2)
        uf.unite(1, 3)
        assertEquals(uf.findRoot(1), 1)
        assertEquals(uf.findRoot(2), 1)
        assertEquals(uf.findRoot(3), 1)
        assertEquals(uf.getSize(1), 3)
        assertEquals(uf.getSize(2), 3)
        assertEquals(uf.getSize(3), 3)
        assertTrue(uf.isSame(1, 2))
        assertTrue(uf.isSame(2, 3))
        assertTrue(uf.isSame(3, 1))
        uf.unite(4, 5)
        uf.unite(5, 1)
        assertEquals(uf.findRoot(4), 1)
        assertEquals(uf.findRoot(5), 1)
        assertEquals(uf.getSize(1), 5)
        assertEquals(uf.getSize(4), 5)
    }
}
