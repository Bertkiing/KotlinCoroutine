package basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description:Global coroutines are like daemon threads
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#global-coroutines-are-like-daemon-threads
 *
 * 在GlobalScope中启动的协程(像守护线程(daemon thread)一样),不会使进程保持活动状态,
 * 再次强调一下,GlobalScope 中启动的协程.
 */
fun main() = runBlocking{
    GlobalScope.launch {
        repeat(1000){
            println("I' m sleeping$it...${Thread.currentThread().name}")
            delay(500L)
        }
    }
    delay(1300L)
}