package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#structured-concurrency
 *
 * 不推荐使用GlobalScope的原因:
 * 1. a top-level coroutine,Even though it is light-weight, it still consumes some memory resources while it runs.
 * 2. error-prone. Having to manually keep references to all the launched coroutines and join them is error-prone.
 *
 * 正确的做法:
 * Every coroutine builder,including runBlocking, adds an instance of CoroutineScope to the scope of its code block.
 * We can launch coroutines in this scope without having to join them explicitly, because an outer coroutine does not
 * complete until all the coroutine launched in its scope complete.
 *
 * 到目前为止,我们所见到的coroutine builder :
 * 1. GlobalScope.launch{}
 * 2. runBlocking{}
 * 3. launch{ }
 * 4. coroutineScope{ } later soon
 */
fun main() = runBlocking {
        launch {
            delay(1000L)
            print("world...${Thread.currentThread().name}")
        }
    println("Hello,...${Thread.currentThread().name}")
}