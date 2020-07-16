package coroutineContextAndDispatchers

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description:　Jumping between threads
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#jumping-between-threads
 *
 * 这里关注点有两个：
 * １．Using runBlocking with an explicitly specified context,and using the withContext() to change the context of a
 * coroutine. (我们可以使用withContext()来改变协程的上下文，ＰＳ：协程并没有改变)
 *
 * ２．Kotlin　标准库中的 use()函数 可以　释放线程池创建的线程，当他们不再被使用时．
 *
 */

fun main() {

    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}