package composingSuspendingFunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.ArithmeticException

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Structured concurrency with async
 *
 * Cancellation is always propagated through coroutines hierarchy.
 * (协程的取消操作总是通过协程层次传播的)
 *
 */

fun main() = runBlocking<Unit> {
    try {
        failedConcurrentSum()
    }catch (e:ArithmeticException){
        println("Computation failed with ArithmeticException...")
        e.printStackTrace()
    }
}

suspend fun failedConcurrentSum():Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE)
            369
        }finally {
            println("First child was cancelled...${Thread.currentThread().name}")
        }
    }

   val two =  async<Int> {
        println("Second child throws an exception...${Thread.currentThread().name}")
        throw ArithmeticException()
    }

     one.await() + two.await()
}

