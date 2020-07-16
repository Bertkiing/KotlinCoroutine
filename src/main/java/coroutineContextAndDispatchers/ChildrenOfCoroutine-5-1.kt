package coroutineContextAndDispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description:　Children of a coroutine
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#children-of-a-coroutine
 * 主要讨论的是:GlobalScope 和 CoroutineScope的区别
 *
 * When a coroutine is launched in the CoroutineScope of another coroutine, it inherits its context via
 * CoroutineScope.coroutineContext and the Job of the new coroutine becomes a child of the parent coroutine's job.
 * (当一个协程发射在另一个协程的作用域内，则它通过CoroutineScope.coroutineContext继承了其上下文，并且新协程的Ｊob将变为父协程的Ｊob)
 *
 * When the parent coroutine is cancelled , all its children are recursively cancelled,too.
 * (当父协程被取消时,其所有的子协程也都会递归地被取消)
 *
 * --------------
 * However,when GlobalScope is used to launch a coroutine ,there is no parent for the job of the new coroutine.
 * (但是,当使用GlobalScope用于发射一个协程,则不存在新协程Job的父协程概念.)
 * It is therefore not tied to the scope it was launched from and operates independently.
 * (因此,GlobalScope独立运行,与其启动作用域无关.)
 *
 *
 * 注意这里的句型: It is therefore not tied to .....
 */

fun main() = runBlocking<Unit> {
    // launch a coroutine to process some kind of incoming request
    val request = launch {
        // it spawns two other jobs, one with GlobalScope
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("coroutineContextAndDispatchers.main: Who has survived request cancellation?")
}