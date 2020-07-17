package flowsBasic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: intermediate flow operators(中间流操作符)
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#intermediate-flow-operators
 *
 * 为什么会称为中间流(intermediate flow)?
 * 因为存在 上游流(upstream flow) 和 下游流(downstream flow)的概念  PS: 这些概念在学习RxJava的时候就应该知晓
 * --------------------------------------------------------------------------------------
 * Flows can be transformed with operators, just as you would with collections and FlowsBasic.sequences.
 * (Flows 可以通过操作符进行转化,就像你使用 Collections 和 Sequences 一样)
 *
 * Intermediate operators are applied to an upstream flow and return a downstream flow.
 * (中间操作符 被应用于上游流(Upstream flow)并返回给下游流(Downstream flow))
 *
 * These operator are cold, just like FlowsBasic.flows are.  A call to such an operator is not a suspending function itself.
 * (这些操作符(中间操作符)就像Flow一样,也是cold. 即: 调用此操作符本身不是挂机函数)
 *
 * It works quickly, returning the definition of a now transformed flow.
 * (它本身运行很快,返回一个新转换流的定义)
 *
 * -------------------------------------------------------------------
 *
 * 中间操作符: map , filter... and so on.
 *
 * The important difference to FlowsBasic.sequences is that blocks of code inside these operators can call suspending functions.
 * (和 Sequences的一个重要区别在于 这些操作符内部的代码块可以调用 挂起函数.)
 *
 */

suspend fun performRequest(request:Int):String{
    delay(100L)
    return "response $request"
}


fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .map { request -> performRequest(request) }
        .collect{ response -> println(response)}
}