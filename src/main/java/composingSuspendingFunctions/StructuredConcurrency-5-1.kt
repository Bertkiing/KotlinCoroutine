package composingSuspendingFunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Structured concurrency with async
 * https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#structured-concurrency-with-async
 *
 * the async coroutine builder is defined as an extension on CoroutineScope,we need to have it in the scope.
 * (协程建造者async 被定义为CoroutineScope的一个扩展,所以我们需要将其放在CoroutineScope中.)
 *
 */
fun main() = runBlocking {
    val measureTimeMillis = measureTimeMillis {
        println("The content is: ${merge()}")
    }
    println("Complete in $measureTimeMillis ms") // 1017ms
}

/**
 * This way, if something goes wrong inside the code of the  function and it throws an exception,all
 * the coroutines that were launched in its scope will be cancelled.
 */
suspend fun merge():String = coroutineScope {
    val first = async { first() }
    val second = async { second() }
    first.await() + second.await()
}