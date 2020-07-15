package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description: Extract function refactoring
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#extract-function-refactoring
 *
 * 挂起函数(Suspend function)
 * Suspending functions can be used inside coroutines just like regular functions, but their additional feature
 * is that they can, in turn, use other suspending functions to suspend execution of a coroutine.
 * 与常规函数一样,suspend function 可以在协程中使用,但是其另一个特征是: 挂起函数可以使用其它的挂起函数来挂起协程的执行.
 *
 * 特别声明:
 * 在这个例子中,将launch替换为CoroutineScope,效果不一样.
 *
 */
fun main() = runBlocking {
    launch {
        pay()
    }
    println("先把东西给我...${Thread.currentThread().name}")
}

suspend fun pay() {
        delay(1000L)
        println("我来付款...${Thread.currentThread().name}")
}
