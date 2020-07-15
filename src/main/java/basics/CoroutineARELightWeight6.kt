package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Author Bertking
 * @Date 2020/7/14
 * @description: Coroutine ARE light-weight
 * https://kotlinlang.org/docs/reference/coroutines/basics.html#coroutines-are-light-weight
 */

fun main() = runBlocking {
    repeat(100000){
        launch {
            delay(1000L)
            print("*")
        }
    }

}