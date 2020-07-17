package coroutineContextAndDispatchers

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Dispatchers and threads
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#dispatchers-and-threads
 *
 *  dispatcher :调度器,分配器,调度程序,调度者 (PS:在计算机领域,这个单词的出现频率相当之高.)
 *  --------------------------------------------------------------------------
 *  The coroutine context includes a coroutine dispatcher that determines what thread or threads the corresponding
 *  coroutine uses for its execution.
 *  (协程上下文(Coroutine Context)包含了一个协程分配器(Coroutine Dispatchers),该分配器决定了对应的协程由哪个线程来执行.)
 *
 *  The coroutine dispatchers can confine coroutine execution to a specific thread, dispatch it to a thread pool,or let
 *  it run unconfined.(协程分配器可以限定协程的运行到特定的线程或者线程池...)
 *
 *  confine:限制,限定,局限于
 *
 *  All coroutine builders (like launch and async) accept an optional CoroutineContext parameter that can be used to
 *  explicitly specify the dispatcher for the new coroutine and other context elements.
 *  (所有的协程建造者都可接受一个可选的参数 CoroutineContext ,其用来为新的协程和其它的上下文元素显式地指定分配器.)
 *
 * 1. When launch{...} is used without parameters,it inherits the context (and thus dispatcher) from the CoroutineScope
 *  it is being launched from.
 *  launch{...} : 从其被发射的CoroutineScope 继承 the context(and thus dispatchers)
 *
 * 2. launch(Dispatchers.Default){...},这里我们首先要明白,当协程在GlobalScope被发射时,才被称为 default dispatchers
 *  其使用的是共享的后台线程池.
 *  即: launch(Dispatchers.Default){...} 和 GlobalScope.launch{...}的分配器 是一样的.都是Default.
 *
 * 3. launch(Dispatchers.Unconfined){...} 是一个特殊的分配器.后面讨论
 */

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch { // context of the parent, coroutineContextAndDispatchers.FlowsBasic.main runBlocking coroutine
        println("coroutineContextAndDispatchers.FlowsBasic.main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with coroutineContextAndDispatchers.FlowsBasic.main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO){
        println("IO: I'm working in thread ${Thread.currentThread().name}")
    }

    launch(newFixedThreadPoolContext(6,"fixed")){
        println("newFixedThreadPoolContext: I'm working in thread ${Thread.currentThread().name}")
    }



    // Android 特有的
//    launch(Dispatchers.Main){
//        println("Main: I'm working in thread ${Thread.currentThread().name}")
//    }
}