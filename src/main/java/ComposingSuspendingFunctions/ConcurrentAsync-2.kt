package ComposingSuspendingFunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Concurrent using async
 * https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#concurrent-using-async
 *
 * Now we want to get the answer faster,by doing both concurrently ? This is where async comes to help.(这就是async的作用所在)
 *
 * Conceptually,async is just like launch. It starts a separate coroutine which is a light-weight thread that works
 * concurrently with all the other coroutines.
 * (从概念上来讲,async就像launch一样. 它启动一个独立的协程,这是一个和其它协程并发工作的轻量级的线程.)
 *
 *
 * The difference is that launch returns a Job and does not carry any resulting value,
 * while async return a Deferred--- A light-weight non-blocking future that represents a promise to provide a result later.
 * 两者(async,launch)的区别在于: launch 返回一个不携带任何结果的Job.而 async 返回一个Deferred.,一个轻量级的非阻塞的future,
 * 代表了以后提供结果的承诺.
 *
 * You can use .await() on a deferred value to get its eventual result ,but Deferred is also a Job, so you can cancel it
 * if needed.
 * (我们可以通过.await()来获得最终结果.其实Deferred也是Job,我们可以在必要时取消它.)
 *
 * interface Deferred<out T> : Job (Deferred 继承与 Job)
 *
 *
 * deferred 延期的,推迟的
 *
 * Note that concurrency with coroutine is always explicit.(请注意,协程的并发总是显式的)
 *
 */
fun main() = runBlocking {
    val measureTimeMillis = measureTimeMillis {
        val first = async { first() }
        val second = async { second() }
        println("The content:${first.await()}-${second.await()}... ${Thread.currentThread().name}")
    }
    println("Completed in $measureTimeMillis ms") // 1013ms
}

