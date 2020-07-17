package flowsBasic

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Size-limiting operators(限制容量的操作符)
 *
 * Size-limiting intermediate operators like take cancel the execution of the flow when the corresponding limit is
 * reached.(像take 这种限制容量的中间操作符,当对应的限制值达到后,将会取消Flow后面的执行)
 *
 *
 * PS : Size-limiting operators 也属于 中间操作符
 *
 * 注意事项:
 *  Cancellation in coroutines is always performed by throwing an exception, so that all the resource-management functions
 *  (like try { ... } finally { ... } blocks ) operate normally in case of cancellation.
 *  协程的取消一直表现为抛出一个异常,所以在这种取消操作下,我们应该使用try{ ... } finally { ... } 代码块 来处理所有的资源管理方法.
 *
 */
fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("The limit is reached, this line is not execute...")
        emit(3)
    } finally {
        println("Finally in FlowsBasic.numbers")
    }
}

fun main() = runBlocking<Unit> {
    numbers()




        .take(2)
        .collect { value -> println(value) }
}