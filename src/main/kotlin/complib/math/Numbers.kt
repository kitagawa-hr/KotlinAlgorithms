package complib.math

fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

fun Int.pow(x: Double): Double = Math.pow(this.toDouble(), x)
fun Long.pow(x: Double): Double = Math.pow(this.toDouble(), x)

fun isSquare(num: Long): Boolean {
    val sqrt = Math.sqrt(num.toDouble()).toLong()
    return sqrt * sqrt == num || (sqrt + 1L) * (sqrt + 1L) == num
}


fun isPrime(num: Long): Boolean {
    if (num == 1L) return false
    if (num in listOf(2L, 3L)) return true
    if (num % 6 in listOf(0L, 2L, 3L, 4L)) return false
    val sqrt = Math.sqrt(num.toDouble()).toLong()
    for (n in 5..sqrt step 6) {
        if (num % n == 0L) return false
    }
    for (n in 7..sqrt step 6) {
        if (num % n == 0L) return false
    }
    return true
}