package ComposingSuspendingFunctions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Lazily started async
 * https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#lazily-started-async
 *
 * Optionally,async can be made lazy by setting its start parameter to CoroutineStart.LAZY.
 * (可选的,async 通过设置其start参数为CoroutineStart.LAZY,使其懒加载)
 *
 * In this mode it only starts the coroutine when its result is required by await, or its Job's start function is invoked.
 *
 * 下面的代码仅仅调用 await().
 * 虽然 协程运行了,但是却没有并发执行.(违背了async的设计初衷,有点鸡肋...)
 *
 *
 * Note that if we just call await without ComposingSuspendingFunctions.first calling start on individual coroutines, this will lead to sequential
 * behavior, since await starts the coroutine execution and waits for its finish, which is not the intended use-case for
 * laziness.
 * (只调用await将会导致顺序行为,因为await启动协程的执行并且等待其执行结束,这不是懒加载的预定用例)
 *
 * PS: await()启动协程并等待其执行结束
 *
 */
fun main() = runBlocking {
    val measureTimeMillis = measureTimeMillis {
        val first = async(start = CoroutineStart.LAZY) { first() }
        val second = async(start = CoroutineStart.LAZY) { second() }
        println("The content:${first.await()}-${second.await()}... ${Thread.currentThread().name}")
    }
    println("Completed in $measureTimeMillis ms") // 2019ms
}