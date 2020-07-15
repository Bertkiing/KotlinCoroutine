package cancellationAndTimeouts

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description: Making computation code cancellable(使计算型代码可被取消)
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#making-computation-code-cancellable
 *
 *  正如官方文档所言: There are two approaches to making computation code cancellable.
 *  1. The ComposingSuspendingFunctions.first one is to periodically invoke a suspending function that checks for cancellation.
 *  (阶段性调用一个挂起函数来检查取消)
 *  There is a yield function that is a good choice for that purpose.(yield 函数是个不错的选择)
 *
 *  yield():如果可能,让出当前协程的线程or线程池,让其他协程去执行.
 *   Yields the thread (or thread pool) of the current coroutine dispatcher to other coroutines to run if possible.
 *
 *  2. The other one is to explicitly check the cancellation status. (显式的检查协程的取消状态 isActive )
 *  (isActive is an extension property available inside the coroutine via the CoroutineScope object.)
 *
 */

fun main() = runBlocking{
    val start = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = start
        var i = 0
        while (isActive){
            if(System.currentTimeMillis() > next){
                println("Job: I'm sleeping ${i ++}...")
                next += 500L
            }
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main: I'm tired of waiting ! ")
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main:Now I 'm quit safely...")

}