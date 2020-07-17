### Flow 的基础部分
> Flow用于在 **异步地返回多个值** 的情况.


#### 一.创建Flow
> 创建Flow的三种方式:
>  1. flow {...}  PS: most basic one
>  2. flowOf {...}  PS:  发射固定数量的元素
>  3. .asFlow() 将各种集合和Sequences 转化为 Flow
>
>
#### 二. Flow的特性
##### 1. Flow所在的函数不需要使用**suspend**修饰符标记
>因为 Flow is Cold.即 Flow是固态的,只有在有压力(be collected)的时候才会变成液态(流动).
##### 2. Flow的取消操作完全由其所在协程处理
> It is fully transparent for cancellation.(Flow 对于协程的取消操作完全透明化),
>即:Flow adheres to the general cooperative cancellation of coroutines. (Flow 遵守 协程的合作取消 规则)
>

#### 三. Flow的操作符
##### 1.Intermediate operators(中间操作符) :
> 1.1 转换操作符(Transform Operator): **transform** 最基本的

> 1.2 限制容量的操作符(Size-limiting operators):  **take**
>   take操作符将会终止Flow的执行,而协程的取消一直表现为抛出一个异常,所以需要用try{...}finally{....}   
 
>
>中间操作符被应用于上游流,并返回下游流


> 如何理解中间流(Intermediate Flow)?
> 上游流(Upstream Flow) 和 下游流(Downstream Flow)

##### 2.Terminal operators(终止操作符) :
collect操作符是最基础的一个.
> 1. collect :  the most basic one
> 2. 各种(various)转换集合的函数: toList , toSet
> 3. 获取第一个值 fist 和 确保一个Flow只发射 一个值的 single
> 4. 将Flow归纳为一个值的 reduce 和 fold
>
#### 三. Flow的操作符顺序执行
>每个发射值从上游到下游被所有的中间操作符处理后,然后传递给终止操作符

#### 四. Flow的上下文
Flow的所有操作都在发生在调用**协程的上下文**中,Flow的这个特性被称为:保留上下文(Context Preservation)
> 这样的好处是:既不用关心**执行上下文**,也不会阻塞调用者

---
"塞翁失马,焉知非福",在Android开发中,CPU密集型任务需要运行在**Dispatchers.Default** 和 更新UI的代码需要运行在**Dispatchers.Main**.

在使用Kotlin的协程时,**withContext**用来改变上下文.但是Flow的特性:"保留上下文"不允许改变其上下文.否则抛出异常:
> java.lang.IllegalStateException: Flow invariant is violated:

---
##### 1.特殊的操作符(flowOn)
> **flowOn** 操作符可以被用于改变Flow的上下文.但它会改变Flow的默认的顺序特性.