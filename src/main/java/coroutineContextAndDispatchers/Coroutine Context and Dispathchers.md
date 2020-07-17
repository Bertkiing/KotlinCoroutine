[协程中的上下文和分配器](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#coroutine-context-and-dispatchers)

> Coroutines 一直运行在定义在kotlin标准库的 [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/)

> CoroutineContext 是 各种元素的集合.主要元素包括:
>1. [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)
>   1. coroutineContext[Job]?.isActive == true
>2. [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)
>3. [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html):

> launch(Dispatchers.Default){...} 和 GlobalScope.launch{...} 相同.
>
#### Debug Coroutine
> -Dkotlinx.coroutines.debug=on


当父协程被取消,其内部所有的子协程也会被递归地取消.
父协程会一直等待所有的子协程完成.

#### 1
> 可以通过CoroutineName为 协程命名:
>
> runBlocking(CoroutineName("FlowsBasic.main")){...}
#### 2
> 使用操作符 + 来合并Context element.
>
> launch(Dispatchers.Default + CoroutineName("test")){...}
>
#### 3 Coroutine scope
> CoroutineScope();
>
> MainScope();

> Android开发中,LifeCycle已将CoroutineScope内部实现啦.具体请看:LifecycleCoroutineScope
#### 4 Coroutine 使用thread-local
> asContextElement()

