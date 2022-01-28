package kt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

val task1: () -> String = {
    sleep(2000)
    "Hello".also { println("task1 finished $it ${Thread.currentThread().name}") }
}

val task2: () -> String = {
    sleep(2000)
    "World".also { println("task2 finished $it ${Thread.currentThread().name}") }
}

val task3: (String, String) -> String = { p1, p2 ->
    sleep(2000)
    "$p1 $p2".also { println("task3 finished: $it ${Thread.currentThread().name}") }
}

fun joinTest() {
    println("Thread.join begin..")
    lateinit var s1:String
    lateinit var s2:String

    val t1 = Thread{s1 = task1()}
    val t2 = Thread{s2 = task2()}

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    task3(s1, s2)
}

fun synchronizedTest() {
    println("线程锁 synchronized begin..")
    lateinit var s1:String
    lateinit var s2:String

    Thread{
        synchronized(Unit) {
            s1 = task1()
        }
    }.start()

    s2 = task2() // 跑在主线程了

    synchronized(Unit) {
        task3(s1, s2)
    }
}

fun ReentrantLockTest() {
    println("线程锁 ReentrantLock begin..")
    lateinit var s1:String
    lateinit var s2:String

    val lock = ReentrantLock()
    Thread {
        lock.lock()
        s1 = task1()
        lock.unlock()
    }.start()

    s2 = task2()

    lock.lock()
    task3(s1, s2)
    lock.unlock()
}

fun blockingQueueTest() {
    println("阻塞队列 SynchronousQueue begin..")
    lateinit var s1:String
    lateinit var s2:String

    val queue = SynchronousQueue<Unit>() // 内部也是"lock"
    Thread {
        s1 = task1()
        queue.offer(Unit)
    }.start()

    s2 = task2()

    queue.take()
    task3(s1, s2)
}

fun AtomicIntegerTest() {
    println("基于 CAS 的原子类计数 AtomicInteger begin..")
    lateinit var s1:String
    lateinit var s2:String

    val cas = AtomicInteger(2)

    Thread {
        s1 = task1()
        cas.getAndDecrement()
    }.start()

    Thread {
        s2 = task2()
        cas.getAndDecrement()
    }.start()

    while (cas.get() != 0) {}

    task3(s1, s2)
}

fun coroutineTest() {
    println("Coroutine begin..")
    runBlocking {
        val c1 = async(Dispatchers.IO) {
            task1()
        }
        val c2 = async(Dispatchers.IO) {
            task2()
        }
        task3(c1.await(), c2.await())
    }
}

fun singleInstanceTest() {
    println(Unit == Unit)

    var s1 = SingleInstanceClass
    s1.name = "111"
    println(s1.name)

    var s2 = SingleInstanceClass
    s2.name = "222"
    println("s1 = ${s1.name} s2 = ${s2.name}")

    println(s1 == s2)
}

/**
 * "Unit"的理解，单例对象，类似java中的Void
 *
 */
object SingleInstanceClass {
    var name="111"
}
class NormalClass {
    var name="222"
}

fun main() {
//    joinTest()
//    synchronizedTest()
//    ReentrantLockTest()
//    blockingQueueTest()
//    AtomicIntegerTest()
//    coroutineTest()
    // 还有其他：CountDownLatch、CyclicBarrier
//    deadLockTest()
    singleInstanceTest()
}

// "Kotlin的Any类似于Java的Object，没有wait（），notify（）和notifyAll（）方法"
var o1 = Any()
var o2 = Any()

var oo1 = Object()
var oo2 = Object()

/**
 *
 * 死锁发生：(sleep时)
 *
 * 线程1 进入 锁o1
线程2 进入 锁o2
 *
 * wait 时正常：
 *
 *  线程1 进入 锁o1
线程2 进入 锁o2
线程2 进入 锁o1

线程1 进入 锁o2
 *
 *
 * TODO: 没有复现"IllegalMonitorStateException"
 * "什么是死锁？"
 * https://www.cnblogs.com/yaphetsfang/p/11818483.html
 *
 */
fun deadLockTest() {
    Thread{
        // "synchronized"的关键词作用：保证对访问对象、变量的"排他性"的访问权
        synchronized(oo1) {

            println("线程1 进入 锁o1")

//            sleep(1000)  // 持有监视器，不会放弃，易死锁
            oo1.wait(2000) // 放弃监视器，其他对象可以访问

            synchronized(oo2) {

                println("线程1 进入 锁o2")

            }
        }
    }.start()

    Thread{
        synchronized(oo2) {

            println("线程2 进入 锁o2")

            synchronized(oo1) {

                println("线程2 进入 锁o1")

            }
        }
    }.start()
}