
### Composing Suspending Functions
> This sections covers various approaches to composition of suspending functions


默认情况下,协程中的代码和普通代码一样,都是顺序执行的.

协程中的**并发异步**操作使用 **async** 协程构建者

##### 一. async 和 launch 的 区别
1. **launch** 返回一个**不携带任何结果**的**Job**
2. **async** 返回一个**Deferred**,我们可以使用.await()获取最终返回结果

> Deferred is also a Job.

#### 二. async的懒加载
> async(start = CoroutineStart.LAZY){}
>注意其使用场景及start()的调用.


### 三. Structured Concurrency with async 
使用结构化并发避免问题.(不推荐使用Async-style functions)
> async 是定义在CoroutineScope的扩展,我们需要将其在Scope中使用.


Note:
> Job : 在计算机领域,通常译为"作业"