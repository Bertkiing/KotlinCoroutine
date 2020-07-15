package ComposingSuspendingFunctions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Sequential by default
 *
 * https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#sequential-by-default
 * 对于 suspending functions , What do we do if we need them to be invoked sequentially.
 *
 * we use a normal sequential invocation, because the code in the coroutine , just like in the regular code,
 * is sequential by default.(代码在协程中,就像常规代码一样,默认是顺序执行的.)
 *
 * 代码的关注点在于: measureTimeMillis
 *
 */

fun main() = runBlocking {
    val measureTimeMillis = measureTimeMillis {
        println("The content:${first()}-${second()}... ${Thread.currentThread().name}")
    }
    println("Completed in $measureTimeMillis ms") // 2013ms
}

suspend fun first(): String{
    delay(1000L)
    println("First:...${Thread.currentThread().name}")
    return "First invoke..."
}

suspend fun second():String{
    delay(1000L)
    println("Second:...${Thread.currentThread().name}")
    return "Second invoke..."
}