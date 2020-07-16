package coroutineContextAndDispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Job in the context
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#job-in-the-context
 *
 * 这个例子说明：
 * Ｔhe coroutine's Job is  part of its context,and can be retrieved form it using the coroutineContext[Job] expression.
 * 协程中的Job是协程上下文的一部分．可以用coroutineContext[Job]表达式检索到．
 *
 * isActive in CoroutineScope is just a convenient shortcut for coroutineContext[Job]?.isActive == true.
 * CoroutineScope中的isActive仅仅是　coroutineContext[Job]?.isActive == true 的简写．
 *
 */

fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}