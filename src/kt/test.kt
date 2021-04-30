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

    // ��ȷ
    println(aa as? String)

    // ����"null cannot be cast to non-null"
    println(aa as String)

    mListeners.add(NetDispatcherMessageListener())

    /*
    *  �������⣺�ײ�ʹ��java�ӿڣ�������У�鲻һ����ɵ�npe
    *
    *  java.lang.IllegalArgumentException: Parameter specified as non-null is null
    * */
    dispatcher(null)
}