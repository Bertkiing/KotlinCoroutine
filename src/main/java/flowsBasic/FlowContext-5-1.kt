package flowsBasic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Wrong emission withContext
 *
 * However, the long-running CPU-consuming code might need to be executed in the context of Dispatchers.Default and
 * UI-updating code might need  to be executed in the context of Dispatchers.Main.
 * (世事难料,长时间运行的消耗CPU代码 需要运行在 Dispatchers.Default的上下文,而 更新UI的代码则需要被执行在 Dispatchers.Main的上下文中)
 *
 * Usually,withContext is used to change the context in the code using Kotlin coroutines, but code in the flow {...}
 * builder has to honor the context preservation property and is not allowed to emit from a different context.
 * (通常,withContext在Kotlin协程中被用于改变代码的上下文,但是在 flow{...} 的代码 遵守 保留上下文的特性,不能在不同的Context 发射)
 *
 * -------------------------------
 *  java.lang.IllegalStateException: Flow invariant is violated:
 *
 * emission : 放射,发出
 *
 */

fun foos(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun main() = runBlocking<Unit> {
    foos().collect { value -> println(value) }
}