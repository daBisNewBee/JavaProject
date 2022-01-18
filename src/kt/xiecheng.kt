package kt

import kotlinx.coroutines.*
import kotlin.random.Random

/**
 *
 * 协程
 *
 * "不用关注多线程就能够很方便地写出并发操作"
 * "协程能在单线程处理高并发，因为遇到 I/O 自动切换，线程遇到 I/O 操作会等待、阻塞。"
 *
 * org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1
 *
 * 协程和线程相比，有三个比较明显的优势:
 *
 * 1. 减少了线程切换的成本
 * 2. 不需要多线程的锁机制。因为只有一个线程
 * 3. 协程更轻量级。创建一个线程栈大概需要 1M 左右，而协程栈大概只需要几 K 或者几十 K。
 *
 * 协程缺点：
 * 1. 前面的程序看起来在“上串下跳”，所以，协程看起来也没那么好控制
 * 2. 缺点是无法利用多核资源，本质是单核的，它不能同时将单个CPU的多个核用上，协程需要和进程配合才能运行在多CPU上。
 *
 * 为什么要有协程？
 * 1. 任务很多，为每个任务都创建一个线程是不现实的，因为会消耗大量的内存
 *    (进程虚拟内存会占用4GB[32位操作系统], 而线程也要大约4MB)
 *
 * 2. 同时多线程也会给内核的调度管理带来巨大的压力
 *
  */

// 第一种启动方式(runBlocking:T)
// 会阻断当前线程，直到该协程执行结束
fun test() = runBlocking {

    launch {
        println("1")
        println("2")
        println("3")
        println("4")
        println("5")
        println("6")
    }

    launch {
        println("a")
        println("b")
        println("c")
        println("e")
        println("f")
        println("g")
    }
}

fun handleDataOnUI(data1:String, data2:String, data3:String) {
    println(data1)
    println(data2)
    println(data3)
}

fun bingFaTest() {
    GlobalScope.launch(Dispatchers.Default) {
        var data1 = async(Dispatchers.IO) {
            delay(1000)
            return@async "1111"
        }.await()

        var data2 = async(Dispatchers.IO) {
            delay(2000)
            return@async "2222"
        }.await()

        var data3 = async(Dispatchers.IO) {
            delay(3000)
            return@async "3333"
        }.await()

        delay(5000)
        handleDataOnUI(data1, data2, data3)
    }
}

/**
 *
 * "suspend 挂起函数"
 *
 * 1. 因为delay 为挂起函数，所以 printDelayed 也是.
 * 2. suspend函数只能在CoroutineScope(协程作用域)中,或者另外一个suspend函数中调用
 */

suspend fun printDelayed() {
    delay(1000)
    println("这是挂起函数 = ${Thread.currentThread().id} = "
            + System.currentTimeMillis().toString().takeLast(4))
}

/**
 *
 * "runBlocking 启动一个新的线程"
 *
 * 会阻塞当前线程
 *
 * 常用于单元测试的场景，开发中一般不会用到
 *
 */
fun runBlockingTest() = runBlocking {
    println("111111 = ${Thread.currentThread().id} = "
            + System.currentTimeMillis().toString().takeLast(4))
    printDelayed()
    println("333333 = ${Thread.currentThread().id} = "
            + System.currentTimeMillis().toString().takeLast(4))
}

/**
 *
 *  "launch表示启动一个新的协程"
 *
 *  不会阻塞当前线程(TODO: 实际会阻塞)
 *
 *  launch本身和runblocking一样不是suspend函数
 *
 *  111111 = 1 = 7975
    333333 = 1 = 7996
    这里是一个新协程 aaaaaa = 1 = 0006
    这里是一个新协程(新线程) bbbbbb = 11 = 0006
    main 线程 = 1
 *
 */
fun launchTest() = runBlocking {
    println("111111 = ${Thread.currentThread().id} = "
            + System.currentTimeMillis().toString().takeLast(4))
    launch {
        delay(2000)
        println("这里是一个新协程 aaaaaa = ${Thread.currentThread().id} = "
                + System.currentTimeMillis().toString().takeLast(4))
    }

    launch(Dispatchers.Default) {
        delay(4000)
        println("这里是一个新协程(新线程) bbbbbb = ${Thread.currentThread().id} = "
                + System.currentTimeMillis().toString().takeLast(4))
    }

    println("333333 = ${Thread.currentThread().id} = "
            + System.currentTimeMillis().toString().takeLast(4))
}

/**
 * "使用Job控制协程"
 *
 *  launch 返回一个 job
 *
 * 等待：job.join
 * 取消：job.cancel
 */
fun jobTest() = runBlocking{
    println("111111 = ${Thread.currentThread().id} = " + System.currentTimeMillis().toString().takeLast(4))
    var job = launch {
        // 这里启动异步任务
        delay(1000)
        println("222222 = ${Thread.currentThread().id} = " + System.currentTimeMillis().toString().takeLast(4))
    }
    println("333333 = ${Thread.currentThread().id} = " + System.currentTimeMillis().toString().takeLast(4))
    job.join()
}

/**
 * "使用 async 获得协程结果"
 *
 * Deferred的await()方法可以立即获得协程的结果
 *
 * 用于异步执行耗时任务，并且需要返回值（如网络请求、数据库读写、文件读写），在执行完毕通过 await() 函数获取返回值。
 *
 * 带返回值的launch函数
 */
// 实例：卖火车票
fun saleTickets() = runBlocking {
    //要卖出的票总数
    var ticketsCount = 10
    //售票员数量
    val salerCount = 4
    //此列表保存async返回值状态,用于控制协程等待
    val salers = mutableListOf<Deferred<Unit>>()
    repeat(salerCount) {
        var deferred = async {
            while (ticketsCount > 0) {
                println(System.currentTimeMillis().toString().takeLast(4)
                        + " 第 $it 个售票员 卖出第 $ticketsCount 张票.  当前线程 = "
                        + Thread.currentThread().name)
                ticketsCount--
                var random = Random.nextInt(10) + 1
                delay(random * 100L)
            }
        }
        salers.add(deferred)
    }
    salers.forEach { it.await() }
}

fun main() {
//    test()
//    runBlockingTest()
//    launchTest()
//    jobTest()
    bingFaTest()
//    saleTickets()
    println("main 线程 = " + Thread.currentThread().id)
}