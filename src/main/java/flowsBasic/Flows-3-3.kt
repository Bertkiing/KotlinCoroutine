package flowsBasic

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Flow cancellation
 *
 * 第一点: Flow 完全 由协程的取消操作控制.
 *
 * Flow adheres to the general cooperative cancellation of coroutines.
 * (Flow 遵守 协程的合作取消 规则)
 * However, flow infrastructure does not introduce additional cancellation points.
 * (然而,Flow 设施 不会引入额外的取消点)
 * It is fully transparent for cancellation.(Flow 对于协程的取消操作完全透明化)
 *   ----------------------------------------------
 *
 * 第二点: 只有当Flow在一个可被取消的挂起方法中被挂起时,方可被取消.
 *
 * As usual, flow cancellation can be cancelled when the flow is suspended in a cancellable suspending function, and
 * cannot be cancelled otherwise.
 * ------------------------------------------------------
 *
 * In a simpler word, 对于Flow的取消操作,只需要看协程的取消机制即可.
 *
 * 这里展示:withTimeoutOrNull()
 *
 */

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(2500L) {
        flows().collect { i ->
            println(i)
        }
    }
    println("Done")
}