package coroutineContextAndDispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Debugging coroutines and threads
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#debugging-coroutines-and-threads
 *
 * 协程在定位追踪上的难点:
 * Coroutines can suspend on one thread and resume on another thread. (PS: suspend VS resume)
 * (协程可以在一个线程挂起,而在另一个线程中恢复)
 *
 * Even with a single-threaded dispatcher it might be hard to figure out what the coroutine was doing, where,and when.
 * (即使在单线程的分配器中,也可能很难确定协程正在做什么,在哪里以及什么时间)
 *
 * 解决办法:
 * The common approach to debugging applications with threads is to print the thread name in the coroutineContextAndDispatchers.FlowsBasic.log file on each coroutineContextAndDispatchers.FlowsBasic.log
 * statement.The feature is universally supported by logging frameworks.
 * (常见方案是Debug应用的线程,在每个日志文件中打印线程名称,这个特性通常被日志框架所支持)
 *  When using coroutines, the thread name alone does not
 * give much of a context, so kotlinx.coroutines includes debugging facilities to make it easier.
 * (当使用协程时,仅仅线程名称不足以提供更多的信息,所以kotlinx.coroutines 包含了 debug 工具)使其变得容易.
 *
 * 配置了-Dkotlinx.coroutines.debug ＪＶＭ选项时，Thread.currentThread().name 可以打印出协程的名称
 *
 */


fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking<Unit> {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}