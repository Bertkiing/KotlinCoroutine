package cancellationAndTimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Timeout
 *
 * Since cancellation is just an exception all resources are closed in the usual way.
 *
 * 处理超时异常的两种方案:
 *
 * 1. try{...}catch(e:TimeoutCancellationException){...} block if you need to do some additional action specifically
 * on any kind of timeout.(try...catch语句)
 *
 * 2. use the withTimeoutOrNull function that is similar to withTimeout but returns null on timeout instead of
 * throwing an exception.(withTimeoutOrNull 超时return null 而不是抛出异常)
 *
 */

fun main() = runBlocking{
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i...${Thread.currentThread().name}")
            delay(500L)
        }
        "Done"
    }
    println("The reuslt :$result")
}