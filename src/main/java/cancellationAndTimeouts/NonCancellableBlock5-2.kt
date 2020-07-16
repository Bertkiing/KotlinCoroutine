package cancellationAndTimeouts

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Run non-cancellable block
 *
 * However,in  the rare case when you need to suspend in a cancelled coroutine | you can wrap the corresponding code
 * in withContext(NonCancellable){...},using withContext function and NonCancellable context .
 * (但是,在极少数情况下,当你需要在取消的协程中进行挂起操作时,我们可以使用 withContext函数和NonCancellable上下文)
 *
 * 值得注意的是:
 *  withContext(NonCancellable){...}的用法
 *
 */

fun main() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Job: I'm sleeping $i...${Thread.currentThread().name}")
                delay(1000L)
            }
        } finally {
            withContext(NonCancellable) {
                println("Job: I'm running finally...${Thread.currentThread().name}")
                delay(1000L)
                println("job,I can continue running because I'm non-cancellable...${Thread.currentThread().name}")
            }
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main:I'm tired for waiting...")
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main:Now I can quit safely ... ${Thread.currentThread().name}")
}