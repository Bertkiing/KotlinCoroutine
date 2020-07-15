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
 * The unconfined dispatchers is app
 *
 */

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}