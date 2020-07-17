package coroutineContextAndDispatchers

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Unconfined VS confined dispatcher(限定和非限定的分配器)
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#unconfined-vs-confined-dispatcher
 *
 * The Dispatchers.Unconfined coroutine dispatcher starts a coroutine in the caller thread,but only until the first
 * suspension point.
 * (指定未限定的分配器的协程 开启协程在所调用的线程上,但是仅仅在第一个挂起点启动)
 * After suspension it resumes the coroutine in the thread that is fully determined by the suspending function that was
 * invoked.
 * (在挂起之后,它将恢复调用挂起函数并完全被挂起函数所决定的线程中的协程)
 *
 * The unconfined dispatchers is appropriate for coroutines which neither consume CPU time nor update any shared data
 * (like UI) confined to a specific thread.
 * (未限定分配器 适合 这些协程---既不消耗CPU 也不局限于特定线程中跟新任何共享的数据(如:UI))
 *
 * 对于未限定分配器的使用场景:
 * The unconfined dispatcher is an advanced mechanism that can be helpful in certain corner cases where dispatching
 * of a coroutine for its execution later is not needed or produces undesirable side-effects,because some operation in
 * a coroutine must be performed right away. The unconfined dispatcher should not be used in general code.
 *
 * 未限定分配器( The unconfined dispatcher)是一种先进的机制.在不需要为以后运行的协程分配任务,或者产生不希望的副作用的情况下非常有用.
 * 因为协程的有些操作必须立刻(right away)运行.
 *
 * PS : 未指定的分配器不应被使用在普通的代码中.
 *
 * in certain corner cases : 在某些情况下
 */

fun main() = runBlocking<Unit> {
    // resumes in the default executor thread that the delay function is using.
    launch(Dispatchers.Unconfined) { // not confined -- will work with coroutineContextAndDispatchers.FlowsBasic.main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }

    launch { // context of the parent, coroutineContextAndDispatchers.FlowsBasic.main runBlocking coroutine
        println("coroutineContextAndDispatchers.FlowsBasic.main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("coroutineContextAndDispatchers.FlowsBasic.main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}