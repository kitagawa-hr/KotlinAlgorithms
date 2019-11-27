package complib.math

interface Monoid<T> {
    fun combine(a: T, b: T): T
    fun empty(): T
}

object IntMonoid : Monoid<Int> {
    override fun combine(a: Int, b: Int): Int = a + b
    override fun empty(): Int = 0
}

