package basics

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description:
 *
 * Scope builder:https://kotlinlang.org/docs/reference/coroutines/basics.html#scope-builder
 *
 * In addition to the coroutine scope provided by different builder, it is possible to declare your own scope
 * using the coroutineScope builder. It creates a coroutine scope and does not complete until all launched children
 * complete.(除了由不同的协程构造器提供的协程作用域外,还可以通过 coroutineScope builder声明自己的作用域)
 *
 * 非常不错的句式: In addition to ... , it is possible to .....除了...之外,还可以....
 *
 * runBlocking 和 coroutineScope 由于它们都需要等待其内部子模块执行完成,所以看起来比较相似. 其实两者的主要区别在于:
 * 1. runBlocking方法为了等待而阻塞当前线程(current thread),而 coroutineScope  just suspend(挂起),为其它用途而释放底层线程.
 *  基于此,runBlocking{}是一个常规方法(regular function),而coroutineScope 是一个挂起方法(suspend function)
 *
 *
 */
fun main() = runBlocking{
    launch {
        delay(200L)
        println("It's from runBlocking...${Thread.currentThread().name}")
    }

    coroutineScope {
        launch {
            delay(500)
            println("It's from nested launch...${Thread.currentThread().name}")
        }
        delay(100L)
        println("It's from coroutine scope...${Thread.currentThread().name}")
    }

    println("Coroutine scope is over...${Thread.currentThread().name}")
}