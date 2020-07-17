package composingSuspendingFunctions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description:
 * 这里采用 start()方式
 *
 * This way : the control is given to the programmer on when exactly to start the execution by calling start.
 *
 * 通过打印时间,我们可以看出这样做是:并发执行的
 *
 *  Lazily starts async 的使用场景:
 *
 * The use-case for async(start = CoroutineStart.LAZY) is a replacement for the standard lazy function in cases when
 * computation of the value involves suspending functions.
 * (在值的计算涉及挂起函数时,才是 async(start = CoroutineStart.LAZY) 替换 lazy函数的应用场景)
 *
 *
 */

fun main() = runBlocking {
    val measureTimeMillis = measureTimeMillis {
        val first = async(start = CoroutineStart.LAZY) { first() }
        val second = async(start = CoroutineStart.LAZY) { second() }
        first.start()
        second.start()
        println("The content:${first.await()}-${second.await()}... ${Thread.currentThread().name}")
    }
    println("Completed in $measureTimeMillis ms") // 1013ms
}