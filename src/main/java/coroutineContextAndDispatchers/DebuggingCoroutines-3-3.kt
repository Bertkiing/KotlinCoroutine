package coroutineContextAndDispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Naming coroutine for debugging
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#naming-coroutines-for-debugging
 *
 * 为了Debug方便,我们可以给协程命名(CoroutineName)
 *
 * Automatically assigned ids are good when coroutines coroutineContextAndDispatchers.FlowsBasic.log often and you just need to correlate coroutineContextAndDispatchers.FlowsBasic.log records coming from
 * the same coroutine.
 * (当协程经常输出日志,自动分配ID是非常好的,我们只需要关联来自同一协程的日志记录.)
 *
 * However, when a coroutine is tied to the processing of a specific request or doing some specific background task, it
 * is better to name it explicitly for debugging purpose.
 * (但是,当一个协程和处理一个特殊的请求或者做一些特殊的后台任务相关联时,为了Debug目的,最好显式地命名它.)
 *
 * CoroutineName context element serves the same purpose as the thread name.
 * (CoroutineName context元素与线程名称的作用相同)
 *
 * CoroutineName ---> AbstractCoroutineContextElement -->Element ---> CoroutineContext
 *
 */
fun main() = runBlocking(CoroutineName("coroutineContextAndDispatchers.FlowsBasic.main")) {
    log("Started coroutineContextAndDispatchers.FlowsBasic.main coroutine")
    // run two background value computations
    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log("Computing v1")
        252
    }
    val v2 = async(CoroutineName("v2coroutine")) {
        delay(1000)
        log("Computing v2")
        6
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}