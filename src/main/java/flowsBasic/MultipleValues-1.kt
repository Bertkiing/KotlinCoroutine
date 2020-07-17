package flowsBasic

import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description:Asynchronous Flow(异步流)
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow
 *
 * Suspending functions asynchronously returns a single value, but how can we return multiple asynchronously computed
 * values? This is where Kotlin Flows come in.
 * (挂起函数异步地返回一个值,但是我们如何返回异步计算的值呢? 这就是Flows的用武之地)
 *
 *
 * 第一步:先考虑返回多个返回值的实现:
 * 1. using collections.(使用集合)
 * 2. FlowsBasic.sequences (阻塞线程)
 *
 */

fun list() = listOf<Int>(1, 2, 3)

/**
 * block the FlowsBasic.main thread
 */
fun sequences(): Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(1000L)
            yield(i)
        }
}




fun main() {
    list().forEach { value -> println(" $value") }
    println("---------------------------------------------- ")
    val measureTimeMillis = measureTimeMillis {
        sequences().forEach { i -> println(" $i") }
    }
    println("Sequences:time:$measureTimeMillis ms") // 3010ms
    println("---------------------------------------------- ")
}


