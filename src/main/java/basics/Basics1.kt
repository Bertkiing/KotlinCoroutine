package basics

import kotlinx.coroutines.*


class Basics {

}

/**
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#your-ComposingSuspendingFunctions.first-coroutine
 * Note that : delay(1000L) is non-blocking , because we can see ComposingSuspendingFunctions.first print "Hello,"
 *
 *
 * coroutine builder:
 * 1. GlobalScope.launch{}
 * 2. runBlocking{  }
 *
 */
fun main() = runBlocking<Unit> {
    // launch a new coroutine in background and continue
    GlobalScope.launch {
        delay(1000L) // non-blocking delay for 1 ComposingSuspendingFunctions.second
        println("world...${Thread.currentThread().name}")
    }
    println("Hello,...${Thread.currentThread().name}") // the basics.cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main thread is continue while the coroutine is delayed
//    Thread.sleep(1500L) // blocking the basics.cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.main thread to keep JVM alive
        delay(1500L)
}