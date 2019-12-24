package complib.graph

import kotlin.test.assertEquals
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertArrayEquals

class BreadthFirstSearchTest {
    @Nested
    inner class DFSTest {
        private val g = AdjacentListGraph(
            MutableList(10) { Vertex<Int, Int>(it) }
        )

        @BeforeEach
        fun setUp() {
            """
              1
             / \
            8   3
            |  /  \
            4 6    5
              |    / \
              2   7   9 - 0

            """.trimIndent()
            g.addEdge(1, 8, 1)
            g.addEdge(8, 4, 1)
            g.addEdge(1, 3, 1)
            g.addEdge(3, 6, 1)
            g.addEdge(3, 5, 1)
            g.addEdge(5, 7, 1)
            g.addEdge(5, 9, 1)
            g.addEdge(6, 2, 1)
            g.addEdge(9, 0, 1)
        }
        @DisplayName("Test distances from node 1")
        @Test
        fun testDistancesFrom() {
            assertArrayEquals(
                arrayOf(4, 0, 3, 1, 2, 2, 2, 3, 1, 3),
                distancesFrom(g, 1, 0) { a, b -> a + b }
            )
        }
        @DisplayName("Shortest Path from node 1")
        @Test
        fun testShortestPath() {
            assertEquals(
                listOf(1, 3, 5, 9, 0),
                shortestPath(g, 1, 0)
            )
        }
    }
}
