import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Flow context
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#flow-context
 *
 * Collection of a flow always happens in the context of the calling coroutine.
 * (Flow的 collection 一直发生在调用协程的Context中.)
 *
 * This property on a flow is called context preservation(Flow的这个特性被称为:上下文保留)
 *
 * So, by default, code in the flow {...} builder runs in the context that is provided by a collector of the corresponding
 * flow.
 * (所以,默认情况下,flow{...}的代码的上下文由对应的Flow的collector提供.)
 *
 * This is the perfect default for fast-running or asynchronous code that does not care about the execution context
 * and does not block the caller.
 * (这是快速运行或者异步代码的最佳默认实现,既不用关心执行上下文,也不会阻塞调用者)
 *
 * ------------------------------------------------------------
 *
 * Flow的 "保留上下文 " 还真是特好的.
 *
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun foo(): Flow<Int> = flow {
    log("Started foo flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    foo().collect { value -> log("Collected $value") }
}