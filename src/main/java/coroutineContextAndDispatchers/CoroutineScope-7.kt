package coroutineContextAndDispatchers

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Coroutine Scope (协程作用域)
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#coroutine-scope
 *
 * Let us put our knowledge about contexts, children and jobs together.
 *
 * 本节的主要内容是关于:CoroutineScope的概念和使用
 *
 * Android来假设场景:
 * 就选Activity来讲,它是具有生命周期的对象,但它不是协程.
 * 显然Activity中可能存在多种协程用于进行异步操作:拉取&更新数据,做动画等. 在Activity销毁时,为了避免内存泄漏,我们肯定要将这些协程取消掉.
 * 我们都知道协程建造者的返回值是Job,So我们可以将其与Activity的生命周期相关联.
 *
 * 但是Kotlinx.coroutine已经为我们提供了现成的CoroutineScope.
 *
 * A CoroutineScope instance can be created by the CoroutineScope() or MainScope() factory functions.
 * (我们可以通过CoroutineScope() 或者 MainScope()工厂方法来创建 CoroutineScope的实例)
 *
 *
 * 下面来仿写一个Android应用,验证一下.
 *
 *
 * (Android Jetpack 中 androidx.lifecycle已经对其进行了封装,完全不需要我们做任何事情.参见:LifecycleScope)
 *
 */

class Activity{

    private val mainScope = CoroutineScope(Dispatchers.Default)


    fun doWork(){
        repeat(10){i ->
            mainScope.launch {
                delay((i+1)*200L)
                println("Coroutine $i is done...${Thread.currentThread().name}")
            }
        }
    }

    fun destroy(){
        mainScope.cancel()
    }

}

fun main() = runBlocking {
    val activity = Activity()
    activity.doWork()
    println("Launched coroutines...")
    delay(500L)
    println("Destroying activity")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
}


