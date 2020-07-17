package flowsBasic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/16
 * @description: Flows
 *
 * 我们可以使用 Flow<T> 代表异步计算的数据流. 其用法与Sequence<Int>类似
 *
 * flow{....} 返回 Flow<T>
 * flow{ ... } 可以挂起,并且其所在函数不必被 suspend修饰符进行标记
 *
 *
 * Values are emitted from the flow using emit function.
 * (使用emit函数发射flow中的值)
 * Values are collected from the flow using collect function.
 * (使用collect函数收集flow中的值)
 */


/**
 * 注意这里:没有suspend修饰符(modifier).
 */
fun flows():Flow<Int> = flow {// flow builder
    println("start FlowsBasic.flows.... ")
    for(i in 1..3){
        delay(1000L) // non-block
//        Thread.sleep(1000L) // block the FlowsBasic.main thread
        println("emit $i ...")
        emit(i) // emit next value
    }
}



fun main() = runBlocking<Unit> {
    // launch a concurrent coroutine to check if the FlowsBasic.main thread is blocked
    launch {
        for (i in 1..3){
            println("I'm not blocked $i")
            delay(1000L)
        }
    }
    // collect the flow
    val measureTimeMillis = measureTimeMillis {
        flows().collect { i ->
            println(i)
        }
    }
    println("flow collect consume time : $measureTimeMillis ms") // 3020 ms
}