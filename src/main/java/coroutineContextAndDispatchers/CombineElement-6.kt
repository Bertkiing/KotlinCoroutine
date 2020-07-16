package coroutineContextAndDispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Combining context elements
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#combining-context-elements
 *
 * 回顾我们前面所学习的内容:
 * 分配器(CoroutineDispatcher) : CoroutineContext
 * 命名(CoroutineName): CoroutineContext
 * 两者都是属于协程的上下文,
 *
 * 当我们需要同时定义他们该怎么做呢? 这就是这节的内容:
 *
 * Sometimes we need to define multiple elements for a coroutine context, We can use the + operator for that.
 * (有时我们需要给一个协程的上下文定义多个元素,我们可以通过操作符 +)
 */
fun main()= runBlocking<Unit> {
    launch(Dispatchers.Default + CoroutineName("rename")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}