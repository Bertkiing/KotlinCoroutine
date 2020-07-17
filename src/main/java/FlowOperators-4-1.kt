import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/17
 * @description: Transform operator(转换操作符)
 *
 * Among the flow transformation operators, the most general one is called transform.
 * (在Flow的转换操作符中,最常用的就是 transform )
 *
 * It can be used to imitate simple transformations like map and filter, as well as implement more complex transformations
 * (它可以用来模拟简单的转换(像 map 和 filter),也能实现更加复杂的转换)
 *
 * Using the transform operator, we can emit arbitrary values an arbitrary number of times.
 * (使用transform操作符,我们可以发射任意次数的任意值)
 * --------------------------------------------------------------------------------------------------------------
 *
 * imitate: 模拟,模仿,仿造
 * arbitrary:任意的,随意所以的,
 */

fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .transform { request ->
            emit("Using transform operator making request $request")
            emit(performRequest(request))
        }
        .collect{ response ->
            println(response)
        }
}