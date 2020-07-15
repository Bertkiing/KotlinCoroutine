package basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#waiting-for-a-job
 * 我们会发现在使用GlobalScope时,JVM会新开一个线程:DefaultDispatcher-worker-1
 */
fun main() = runBlocking{
    /**
     * 如果不小心采用GlobalScope.launch发射协程 + delay过久 , 想想后果都很可怕
     */
    val job = GlobalScope.launch {
        delay(1000L) // 如果这里延迟过长,是很可怕的.
        println("world...${Thread.currentThread().name}")
    }
    println("Hello,...${Thread.currentThread().name}")
    job.join() // wait until child coroutine completes  (same as Thread的 join())
}