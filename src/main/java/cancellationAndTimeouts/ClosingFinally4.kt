package cancellationAndTimeouts

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Closing resources with finally
 *
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#closing-resources-with-finally
 *
 * Cancellable suspending functions throw CancellationException on cancellation which can be handled in the usual way.
 * (我们可以通过常规方式来处理可取消的挂起方法所抛出的CancellationException异常.)
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Job: I'm sleeping $i ... ${Thread.currentThread().name}")
                delay(1000L)
            }
        } finally {
            println("Job:I'm running finally...")
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main:I'm tired to waiting...")
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main:Now I can quit...")
}