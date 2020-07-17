package flowsBasic

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Flows are sequential (Flow是按顺序的)
 *
 * Each individual collection of a flow is performed sequentially unless special operators that operate on multiple
 * FlowsBasic.flows are used.
 * (除非运行在多个Flow的特殊操作符被使用,Flow的每个单独收集器是按顺序执行的.)
 *
 * 注意理解: Each individual collection of a flow的含义
 *  PS : (这个的 collection 理解为"收集物"更贴切一些,收集者(collector)) ,且 collection 还包含着 collector
 *
 *  下面例子中:
 *  collections : filter, map, collect ;
 *  collector : collect
 *
 *
 * The collection works directly in the coroutine that calls a terminal operator. No new coroutines are launched by
 * default .
 * (The collection 直接在调用终止操作符的协程上运行,默认情况下不会启动新协程)
 *
 * --------------------------------------------------------------------------------------------------------------
 * 重点:
 * Each emitted value is processed by all the intermediate operators from upstream to downstream and is then delivered
 * to the terminal operator after.
 * (每个发射值从上游到下游被所有的中间操作符处理后,然后传递给终止操作符)
 *
 * --------------------
 *
 * 下面的例子说明:
 *  (1..5).asFlow() 创建了包含 1,2,3,4,5元素的 Flow.
 *
 *  每个元素都要经过 中间操作符filter, map,最后在 终止操作符 collect 结束.
 *
 *  哈哈哈,有点"大浪淘沙"的味道...
 */

fun main() = runBlocking<Unit> {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 ==0
        }
        .map {
            println("Map $it")
            "string $it"
        }
        .collect{
            println("Collect $it")
        }
}
