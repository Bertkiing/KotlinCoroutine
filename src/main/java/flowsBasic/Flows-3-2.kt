package flowsBasic

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Flows are cold
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#FlowsBasic.flows-are-cold
 *
 * Flows are cold streams similar to sequence - the code inside a flow builder does not run until the flow is collected.
 * (Flow 在 flow{...}并不会运行,直到 该 flow 被 收集)
 *
 * WHY ?
 * This is a key reason the FlowsBasic.flows(which returns a flow) is not marked with suspend modifier.
 * (这就是flows函数不用被suspend修饰符标记的主要原因)
 *
 * In a word. Functions which return a flow type is not marked with suspend modifier, Because the flow is cold.
 *
 * How to understand "Flow is cold"?
 * 常规情况下,Flow是固态的,只有在有压力(be collected)的时候才会变成液态(流动).
 *
 *
 */
fun main() = runBlocking<Unit> {
    println("Calling FlowsBasic.flows...")
    val flow = flows()

    // The flow starts every time it is collected.
    for(i in 1..3){
        println("Calling collect... $i times ")
        flow.collect { i ->
            println(i)
        }
    }
}