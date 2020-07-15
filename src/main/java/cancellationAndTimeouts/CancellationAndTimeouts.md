### Cancellation and Timeouts(取消和超时)

几个重要的概念:
1. CancellationException(可取消异常):
    1. JobCancellationException
    2. TimeoutCancellationException
    
2. launch()返回一个Job(作业),用于取消运行中的协程
    1. job.cancel()  and job.join()
    2. job.cancelAndJoin()
    
3. 在协程内部,CoroutineScope的扩展属性:isActive 可以判断协程的状态

4. 注意方法withContext() 和 NonCancellable **context(上下文)** 的使用.

5. 超时相关的内容:
    1. withTimeout(); // maybe throw an TimeoutCancellationException
    2. withTimeoutOrNull();


### 需要巩固的概念:
> 通过学习Kotlin的内容,我们会发现很多与Java多线程相关的同名函数(PS,功能也大底相同),这就
>需要我们更好滴掌握Java的多线程知识.
1. yield()
2. join()
3. context(上下文)的理解
> 在编程领域,**Context**这个概念是非常常见的,更加需要我们深刻理解其含义.