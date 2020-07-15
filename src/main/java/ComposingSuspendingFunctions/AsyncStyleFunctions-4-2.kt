package ComposingSuspendingFunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Async-style functions
 *
 * 不推荐的原因(Strongly discouraged):
 *
 * 按照官方文档的解释,结合这个代码的运行结果,做以下解释:
 *
 * 假设在 val ComposingSuspendingFunctions.firstAsync = ComposingSuspendingFunctions.repeatFirstAsync() 和 ComposingSuspendingFunctions.firstAsync.await() 之间,代码出现逻辑错误并导致程序运行终止.
 * 但是这种写法会有一个问题,初始化该操作的程序都被终止了,但  ComposingSuspendingFunctions.repeatFirstAsync() 有可能仍然在后台执行.
 *
 * 要想避免这种问题,就需要采用:Structured concurrency(结构化并发)
 *
 */

fun main() {
    val measureTimeMillis = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val firstAsync = repeatFirstAsync()
        val secondAsync = repeatSecondAsync()

        runBlocking {
            delay(10000L)
            1/0
            println("The content is : ${firstAsync.await()} - ${secondAsync.await()}")
        }

    }
    println("Complete in $measureTimeMillis ms") // 1053ms 测试发现没有直接async的速度快
}



fun repeatFirstAsync()= GlobalScope.async {
    repeatFirst()
}

fun repeatSecondAsync() = GlobalScope.async {
    repeatSecond()
}


suspend fun  repeatFirst():String{
    repeat(10){i ->
        delay(1000L)
        println("Job-First: $i...${Thread.currentThread().name}")
    }
    return "1st"
}


suspend fun  repeatSecond():String{
    repeat(10){i ->
        delay(1000L)
        println("Job-Second: $i...${Thread.currentThread().name}")
    }
    return "2nd"
}