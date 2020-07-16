import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Flows are cold
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#flows-are-cold
 *
 * Flows are cold streams similar to sequence - the code inside a flow builder does not run until the flow is collected.
 * (Flow 在 flow{...}并不会运行,直到 该 flow 被 收集)
 *
 * WHY ?
 * This is a key reason the flows(which returns a flow) is not marked with suspend modifier.
 * (这就是flows函数不用被suspend修饰符标记的主要原因)
 *
 *
 */
fun main() = runBlocking<Unit> {
    println("Calling flows...")
    val flow = flows()

    // The flow starts every time it is collected.
    for(i in 1..3){
        println("Calling collect... $i times ")
        flow.collect { i ->
            println(i)
        }
    }
}