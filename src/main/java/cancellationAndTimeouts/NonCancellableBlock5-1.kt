package cancellationAndTimeouts

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Run non-cancellable block
 * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#run-non-cancellable-block
 *
 * Any attempt to use suspending function in the finally block of the previous example causes CancellationException,
 * because the coroutine running this code is cancelled.
 * (在上一个例子中,在finally代码块中进行任何调用挂起函数的尝试都会导致抛出CancellationException,因为运行此代码的协程已经被取消)
 *
 * 在代码中,我们会发现,Cancellation异常并没有打印其堆栈信息(stack trace),原因在于:
 * That is because inside a cancelled coroutine CancellationException is considered to be a normal reason for
 * coroutine completion.(这是因为在一个已取消的协程中,CancellationException被认为是协程完成的正常原因.)
 *
 *
 * Usually,this is not a problem, since all well-behaving closing operations(closing a file ,cancelling a job,
 * or closing any kind of a communication channel) are usually non-blocking and do not involve any suspending
 * functions.
 * (通常情况下,这不算是个问题,因为所有性能良好的关闭操作(如关闭文件,取消作业或者关闭任何类型的通信通道),通常都是非阻塞的,并且不涉及任何挂起功能)
 *
 * PS:since 译为:自从,因为
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Job: I'm sleeping $i...${Thread.currentThread().name}")
                delay(500L)
            }
        }finally {
            println("Job: I'm running finally...")
            delay(1000L)
//            try {
//                delay(1000L)
//            }catch (e:CancellationException){
//                e.printStackTrace()
//            }
            println("Can I running?")// don't print, because throw CancellationException
        }
    }

    delay(1300L)
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.FlowsBasic.main: I'm tired of waiting...")
    job.cancelAndJoin()
    println("cancellationAndTimeouts.cancellationAndTimeouts.ComposingSuspendingFunctions.coroutineContextAndDispatchers.FlowsBasic.main:Now I can quit safely...")


}