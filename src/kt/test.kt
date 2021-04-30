package kt

import java.util.concurrent.CopyOnWriteArrayList

private val mListeners: MutableList<INetDispatchMessageListener> = CopyOnWriteArrayList()

 fun dispatcher(message: Any?) {
    if (message == null) {
        println("message == null")
    }
    for (listener in mListeners) {
        listener.dispatchMessage(message)
    }
}

fun main(args:Array<String>){
    var aa:Any? = null
    println(aa is String)

    // 正确
    println(aa as? String)

    // 报错："null cannot be cast to non-null"
    println(aa as String)

    mListeners.add(NetDispatcherMessageListener())

    /*
    *  复现问题：底层使用java接口，空类型校验不一致造成的npe
    *
    *  java.lang.IllegalArgumentException: Parameter specified as non-null is null
    * */
    dispatcher(null)
}