package complib.math

tailrec fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
tailrec fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

fun Int.pow(x: Double): Double = Math.pow(this.toDouble(), x)
fun Long.pow(x: Double): Double = Math.pow(this.toDouble(), x)

fun isSquare(num: Long): Boolean {
    val sqrt = Math.sqrt(num.toDouble()).toLong()
    return sqrt * sqrt == num || (sqrt + 1L) * (sqrt + 1L) == num
}

fun binomial(n: Int, k: Int): Long = when (k) {
    0 -> 1L
    1 -> n.toLong()
    else -> binomial(n - 1, k - 1) * n / k
}
