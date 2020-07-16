package coroutineContextAndDispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Parental responsibilities
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#parental-responsibilities
 *
 * 上一篇我们知道:
 * 取消的情况: 父协程被取消,则其子协程也会递归地被取消.
 *
 * 这一节来了解一下正常情况:
 * A parent coroutine always waits for completion of all its children.
 * (父协程会一直等待其所有子协程完成.)
 *
 * A parent does not have to explicitly track all the children it launches,
 * and it does not have to use Job.join to wait for them at the end.
 * (父协程不必显式地追踪它所启动的所有子协程,也不必在最后使用Job.join去等待他们)
 *
 */

fun main() = runBlocking<Unit> {
    // launch a coroutine to process some kind of incoming request
    val request = launch {
        repeat(3) { i -> // launch a few children jobs
            launch  {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join() // wait for completion of the request, including all its children-of-a-coroutine
    println("Now processing of the request is complete")
}