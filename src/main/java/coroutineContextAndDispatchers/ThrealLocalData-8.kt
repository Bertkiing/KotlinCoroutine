package coroutineContextAndDispatchers

import kotlinx.coroutines.*

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Thread-local data
 * https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#thread-local-data
 *
 * Sometimes it is very convenient to have an ability to pass some thread-local data to or between coroutines.
 * (有时候,在协程直接传递一些线程私有数据会很方便的.)
 * However,since they are not bound to any particular thread, this will likely lead to boilerplate if done manually.
 * (但是,因为协程不绑定到任何特定的线程,如果人工来做很可能导致错误.)
 *
 * ---------------------------------------------------------------------------------------------------------
 * For ThreadLocal,the asContextElement extension function is here for the rescue.
 * 对于ThreadLocal, 扩展方法asContextElement 可以解决此问题.
 *
 * It creates an additional context element which keeps the value of the given ThreadLocal and restores it every
 * time the coroutine switches its context.
 * (asContextElement 创建一个额外上下文元素,可以每次在协程切换上下文时,保存在ThreadLocal 和恢复它)
 * ---------------------------------------------------------------------------------------------------------
 *
 * It's easy to forget to set the corresponding context element.
 * (这很容易忘记设置对应的上下文元素)
 * The thread-local variable accessed from the coroutine may then have an unexpected value, if the thread running
 * the coroutine is different.
 * (如果线程中运行着不同的协程,则从协程中的协程私有变量可能存在意外值.)
 * To avoid such situations, it is recommended to use the ensurePresent method and fail-fast on improper usages.
 * (为了避免这种情况,推荐使用ensurePresent方法)
 *
 * ThreadLocal has first-class support and can be used with any primitive kotlinx.coroutines provides.
 * It has one key limitation, though: when a thread-local is mutated,
 * a new value is not propagated to the coroutine caller (because a context element cannot track all ThreadLocal object accesses),
 * and the updated value is lost on the next suspension. Use withContext to update the value of the thread-local in a coroutine,
 * see asContextElement for more details.
 *
 * ThreadLocal 具有很好的支持,可以和任何 kotlinx.coroutines 提供的基本类型一起使用.但它有一个关键的限制: 当一个ThreadLocal 改变时,新的值
 * 不会被传播到协程调用者.(因为上下文元素无法跟踪所有的ThreadLocal对象的访问),并且更新的值将在下一次挂起时丢失.
 *
 * 使用withContext可能更新一个协程中的thread-local值,有关更多详细内容,可以参阅 asContextElement.
 *
 * For advanced usage, 可以参看: ThreadContextElement 接口
 *
 */


val threadLocal = ThreadLocal<String?>() // declare thread-local variable

fun main() = runBlocking<Unit> {
    threadLocal.set("coroutineContextAndDispatchers.FlowsBasic.main")
    println("Pre-coroutineContextAndDispatchers.FlowsBasic.main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    job.join()
    println("Post-coroutineContextAndDispatchers.FlowsBasic.main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}