package complib.graph

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GraphTest {
    @Nested
    inner class AdjacentListGraphTest {
        private val g = AdjacentListGraph(
            MutableList(5) { Vertex<Int, Int>() }
        )

        @BeforeEach
        fun setUp() {
            """
            0 ---> 1
            |   /  | ^
            |  /   |  2
            v v    v v
            4 <---  3
            """.trimIndent()
            g.addEdge(0, 1, 1)
            g.addEdge(0, 4, 4)
            g.addEdge(1, 3, 2)
            g.addEdge(1, 4, 3)
            g.addEdge(2, 1, 1)
            g.addEdge(2, 3, 1)
            g.addEdge(3, 4, 1)
        }

        @TestFactory
        fun testGetEdges() = listOf(
            0 to listOf(1, 4),
            1 to listOf(3, 4),
            2 to listOf(1, 3),
            3 to listOf(4),
            4 to listOf()
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("edges from $input should be $expected") {
                assertEquals(expected, g.vertices[input].edges.map { it.to })
            }
        }

        @ParameterizedTest(name = "Weight of edge from {0} to {1} is {2}")
        @CsvSource(
            "0, 1, 1",
            "0, 4, 4",
            "2, 3, 1",
            "1, 2, ",
            "0, 2, ",
            "0, 3, ",
            "2, 4, ",
            "2, 0, "
        )
        fun testGetWeight(from: Int, to: Int, expected: Int?) {
            assertEquals(expected, g.getWeight(from, to))
        }
        @ParameterizedTest(name = "There is edge from {0} to {1} == {2}")
        @CsvSource(
            "0, 1, true",
            "0, 4, true",
            "2, 3, true",
            "1, 2, false",
            "0, 2, false",
            "0, 3, false",
            "2, 4, false",
            "2, 0, false"
        )
        fun testIsAdjacent(from: Int, to: Int, expected: Boolean) {
            assertEquals(expected, g.isAdjacent(from, to))
        }
        @Test
        fun testRemoveEdge() {
            """
            0 ---> 1
                / |
               /  |  2
              v   v v
            4 <--- 3
            """.trimIndent()
            assertEquals(true, g.removeEdge(0, 4))
            assertEquals(false, g.removeEdge(0, 4))
            assertEquals(true, g.removeEdge(2, 1))
            assertEquals(false, g.removeEdge(1, 5))
            listOf(
                0 to listOf(1),
                1 to listOf(3),
                2 to listOf(3),
                3 to listOf(4),
                4 to listOf()
            ).forEach { (input, expected) ->
                DynamicTest.dynamicTest("neighbor of $input should be $expected") {
                    assertEquals(expected, g.vertices[input].edges.map { it.to })
                }
            }
        }
    }
}
