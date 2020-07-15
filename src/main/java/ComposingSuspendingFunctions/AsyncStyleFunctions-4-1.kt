package ComposingSuspendingFunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Bertking
 * @Date 2020/7/15
 * @description: Async-style functions
 * https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#async-style-functions
 *
 * We also can define async-style functions that invoke asynchronously using the async coroutine builder with an
 * explicit GlobalScope reference.
 * (我们可以通过使用async 协程构造者和 显式的GlobalScope 的引用来定义一个异步类型的方法(async-style function).)
 *
 * We name such functions with the "...Async" suffix to highlight the fact that they only start asynchronous computation
 * and one needs to use the resulting deferred value to get the result.
 * (我们可以通过以后缀为Async来表明它是异步函数)
 *
 * Note that these xxxAsync functions are not suspending functions. They can be used from anywhere. However, their use
 * always implies asynchronous (maybe meaning currency) execution of their action with invoking code.
 * (需要注意的是,xxxAsync函数并不是挂起函数,这意味着他们可以被用在任何地方.但是,他们的使用往往暗示着调用代码的异步行为的执行)
 *
 *
 * 更需要我们注意的是:
 * This programming style with async function is provided here only for illustration, because it is a popular style
 * in other programming languages.
 * (因为这是一种在其中编程语言中很流行的方式,所以才在这里提供出来以供说明.)
 *
 * 划重点: strongly discouraged (强烈不推荐)
 *
 *
 */
fun main() {
    val measureTimeMillis = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val firstAsync = firstAsync()
        val secondAsync = secondAsync()

        runBlocking {
            println("The content is : ${firstAsync.await()} - ${secondAsync.await()}")
        }

    }
    println("Complete in $measureTimeMillis ms") // 1053ms 测试发现没有直接async的速度快
}

// The result type is Deferred<T>
fun firstAsync() = GlobalScope.async{
    first()
}

fun secondAsync() = GlobalScope.async {
    second()
}

