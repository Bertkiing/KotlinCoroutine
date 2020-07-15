package cancellationAndTimeouts

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description: Cancellation is cooperative
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#cancellation-is-cooperative
 *
 * Coroutine cancellation is cooperative. A coroutine code has to cooperate to be cancellable.
 * (协程的取消操作是需要配合的.一个协程必须配合才能取消)
 *
 * All the suspending functions in kotlinx.coroutines are cancellable. They check for cancellation of coroutine
 * and throw CancellationException when cancelled.
 * (在kotlinx.coroutines包下的所有挂起方法都是可以被取消的,当进行取消操作时,它们会进行检查协程的取消,并抛出CancellationException)
 *
 * However,if a coroutine is working in a computation and does not  check for cancellation,
 * then it cannot be cancelled,
 * (然而,如果一个协程在工作在计算线程中且没有进行取消操作,那么它是不能被取消的.)
 *
 *
 * 值得注意的是,此例子没有采用挂起方法(delay),而是采用时间累加的方式...,所以我们可以看到 job.cancelAndJoin() not work...
 *
 *
 */

fun main() = runBlocking{
    val start = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = start
        var i = 0

        while (i < 5){
            if(System.currentTimeMillis() > next){
                println("Job: I'm sleeping ${i ++}...")
                next += 500L
//                yield()
            }
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main: I'm tired of waiting ! ")
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.main:Now I 'm quit safely...")

}