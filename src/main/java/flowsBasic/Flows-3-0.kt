package flowsBasic

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Flow builders
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#flow-builders
 *
 * 创建Flow的三种方式:
 * 1. flow {...}  PS: most basic one
 * 2. flowOf {...}  PS:  发射固定数量的元素
 * 3. .asFlow() 将各种集合和Sequences 转化为 Flow
 */

fun main() = runBlocking<Unit> {
    flowOf(4,5,6,7,8).collect {value -> println("From flowOf ... $value")}

    (1..3).asFlow().collect { i -> println("From .asFlow() ... $i") }
}