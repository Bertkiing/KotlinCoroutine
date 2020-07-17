package flowsBasic

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: flowOn operator(操作符flowOn)
 *
 * The exception refers to the flowOn function that shall be used to change the context of the flow emission.
 * (这个例外情况是指:flowOn函数可以用来改变发射Flow的上下文)
 *
 * The correct way to change the context of a flow  is using flowOn function
 * (改变Flow的上下文的正确方式是flowOn)
 * ------------------------------------------------------------------------
 *
 * Notice how flow {...} works in the background thread, while collection happens in the FlowsBasic.main thread.
 * (注意:flow{...}工作在后台线程,然而collection发生在主线程)
 *
 * The flowOn operator has changed the default sequential nature of the flow.
 * (flowOn 操作符改变了流的默认顺序性质)
 * The flowOn operator creates another coroutine for an upstream flow when it has to change the CoroutineDispatcher in
 * its context.
 * (当flowOn运算符必须在其上下文中更改CoroutineDispatcher时，它会为上游流创建另一个协程。)
 *
 */


fun fooos(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

fun main() = runBlocking<Unit> {
    fooos().collect { value ->
        log("Collected $value")
    }
}