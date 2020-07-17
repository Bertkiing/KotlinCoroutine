package flowsBasic

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Terminal flow operators (Flow的终止操作符)
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#terminal-flow-operators
 *
 * Terminal operators on FlowsBasic.flows are suspending functions that start a collection of the flow.
 * (Flow的终止操作符 是 挂起 启动一个Flow的"收集者"的挂起方法)
 *
 * The collect operator is the most basic one,but there are other terminal operators,which can make it easier.
 * (collect操作符是最基础的一个,但是这里还有其它的终止操作符)
 *
 * 1. collect :  the most basic one
 * 2. 各种(various)转换集合的函数: toList , toSet
 * 3. 获取第一个值 fist 和 确保一个Flow只发射 一个值的 single
 * 4. 将Flow归纳为一个值的 reduce 和 fold
 */

fun main() = runBlocking<Unit> {
    val sum = (1..5).asFlow()
        .map { it * it } // square of FlowsBasic.numbers from 1 to 5
        .reduce { a, b -> a + b } // sum them
    println(sum)
    println("--------------------------")

}

