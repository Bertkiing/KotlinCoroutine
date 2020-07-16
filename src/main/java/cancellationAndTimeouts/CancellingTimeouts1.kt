package cancellationAndTimeouts

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description: Cancelling coroutine execution
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#cancelling-coroutine-execution
 *
 *
 * In a long-running application you might need fine-grained control on your background coroutines.
 * 在一个长期运行的应用中,你可能需要对后台执行的协程进行细粒度的控制.
 * The launch function returns a Job that can be used to cancel the running coroutine.
 *
 * job.cancel() 和 job.join() 可以使用 Job 的扩展方法cancelAndJoin()
 *
 */

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            println("Job: I'm sleeping $it....${Thread.currentThread().name}")
            delay(500L)
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main: I 'm tired for waiting...")
   // job.cancel() // cancel the job
    //job.join() // waits for jobs complete
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main: Now I can quit.")

}