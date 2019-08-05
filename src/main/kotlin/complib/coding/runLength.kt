package complib.coding

fun <T> List<T>.runLengthCoding(): List<Pair<T, Int>> {
    val result = mutableListOf<Pair<T, Int>>()
    var key = this[0]
    var count = 0
    for (i in 0 until size) {
        if (this[i] == key) count++
        else {
            result.add(key to count)
            key = this[i]
            count = 1
        }
    }
    result.add(key to count)
    return result
}
