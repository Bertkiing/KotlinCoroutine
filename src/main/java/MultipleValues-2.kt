import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description:
 *
 *  通过这个例子,我们可以发现一个弊端: 必须一次性返回所有值.
 *  Using the List<Int> result type,means we can only return all the values at once.
 *
 *  Flow<T> 是时候上场啦
 *
 *  at once 立刻,马上
 */

// 采用 suspend 修饰符,使其 non-block
suspend fun suspendList(): List<Int> {
    delay(1000L)
    return listOf(1, 2, 3)
}

fun main() = runBlocking<Unit> {
    val measureTimeMillis = measureTimeMillis {
        suspendList().forEach { i ->
            println(" $i")
        }
    }
    println("suspend list: time = $measureTimeMillis ms") // 1015ms
}