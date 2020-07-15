package cancellationAndTimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Timeout
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#timeout
 *
 * The most obvious practical reason to cancel execution of a coroutine is because its execution time has exceeded
 * some timeout.
 * (取消协程执行最明显的现实原因是超时.)
 *
 * While you can manually track the reference to the corresponding Job and launch a separate coroutine to cancel the
 * tracked one after delay. there is a ready to use withTimeout function that does it.
 * (我们可以手动跟踪对应作业的引用,并且发射一个独立的协程在所跟踪的协程延迟之后,取消其执行. 这里还有一个现成的方案: withTimeout函数.)
 *
 * PS : withTimeout()将会抛出 TimeoutCancellationException(CancellationException的子类)
 *
 * PS :
 * Inside a cancelled coroutine CancellationException is considered to be a normal reason for coroutine completion.
 * (在已取消的协程中,CancellationException是不会打印stack trace信息的.)
 */

fun main() = runBlocking {
    withTimeout(1300L){
        repeat(1000){i ->
            println("I'm sleeping $i....")
            delay(500L)
        }
    }
}
